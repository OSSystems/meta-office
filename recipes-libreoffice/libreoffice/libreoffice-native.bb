require ${BPN}.inc

inherit native

DEPENDS += " \
    curl-native \
    boost-native \
    icu-native \
    expat-native \
"

SRC_URI += " \
    file://0006-saxparser-output-calling-parametrs-for-debug.patch \
    file://0007-cppumaker-output-more-detailed-error-message.patch \
    file://0008-cppuhelper-defaultbootstrap-output-debug-information.patch \
"

EXTRA_OECONF += " \
    --enable-debug \
    --enable-dbgutil \
    \
    --enable-python=system \
    --without-x \
    --with-system-curl \
    --with-system-boost \
    --without-boost-date-time \
    --without-boost-iostreams \
    --without-boost-system \
    --with-system-icu \
    --with-system-expat \
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
}
