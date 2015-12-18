require ${BPN}.inc

inherit native

DEPENDS += " \
    curl-native \
    boost-native \
    icu-native \
    expat-native \
    lcms-native \
    nss-native \
    cppunit-native \
    libabw-native \
    libcdr-native \
    libe-book-native \
    libfreehand-native \
"

SRC_URI += " \
    file://0006-saxparser-output-calling-parametrs-for-debug.patch \
    file://0007-cppumaker-output-more-detailed-error-message.patch \
    file://0008-cppuhelper-defaultbootstrap-output-debug-information.patch \
    file://0011-fix-build-for-x-less-cairp-less-build.patch \
"

EXTRA_OECONF += " \
    --enable-debug \
    --enable-dbgutil \
    \
    --enable-python=system \
    --without-x \
    --with-system-curl \
    --with-system-boost \
    --with-system-icu \
    --with-system-expat \
    --with-system-lcms2 \
    --with-system-nss \
    \
    --without-boost-date-time \
    --without-boost-iostreams \
    --without-boost-system \
    --disable-postgresql-sdbc \
    --disable-lotuswordpro \
    --disable-firebird-sdbc \
    --disable-liblangtag \
    --disable-openssl \
    --disable-gltf \
    --disable-collada \
    --disable-scripting-beanshell \
    --disable-scripting-javascript \
    --disable-graphite \
    --disable-pdfimport \
    --disable-orcus \
    --disable-coinmp \
"

do_configure() {
    olddir=`pwd`
    cd ${S}
    aclocal --system-acdir=${B}/aclocal-copy/ -I ${S}/m4
    gnu-configize
    autoconf
    cd $olddir
    oe_runconf
}

# for debugging - we can as we're native
CXXFLAGS += "-g -O0 -DSAL_LOG_INFO -DSAL_LOG_WARN"
LDFLAGS += "-g"

do_compile() {
    # inspired by ${B}/Makefile
    BUILDDIR=${B} oe_runmake -f ${S}/Makefile.gbuild build-tools
    # gengal was not designed for build on its own - we need to add dependencies
    BUILDDIR=${B} oe_runmake Executable_gengal
    BUILDDIR=${B} oe_runmake Library_ucb1
    BUILDDIR=${B} oe_runmake Library_configmgr
    BUILDDIR=${B} oe_runmake Library_fwk
    BUILDDIR=${B} oe_runmake Library_i18npool
}

do_install() {
    install -d ${D}/${bindir}
    for name in ${LOBUILDTOOLS} ; do
        install "${B}/workdir/LinkTarget/Executable/$name" ${D}/${bindir}
    done

    # icu creates a gendict. To avoid conflicts rename in sysroot
    install "${B}/workdir/LinkTarget/Executable/gendict" ${D}/${bindir}/gendict_libre

    # install sdk binaries
    install ${B}/instdir/sdk/bin/* ${D}/${bindir}

    # install libraries and defaults
    install -d ${D}/${libdir}
    for name in `find ${B}/instdir/program -type f` ; do
        install "$name" ${D}/${libdir}
    done

    # move saxparser.rdb to libdir - we'll need it for cross building
    cp -rf ${B}/workdir/Rdb/saxparser.rdb ${D}/${libdir}
    # fix library path - otherwise cross lib is pulled for native saxparse
    sed -i 's:LO_LIB_DIR:URE_INTERNAL_LIB_DIR:g' ${D}/${libdir}/saxparser.rdb

    # gengal script and binary to expected location
    install ${S}/svx/source/gengal/gengal.sh ${D}/${bindir}/gengal
    mv ${D}/${libdir}/gengal.bin ${D}/${bindir}
}
