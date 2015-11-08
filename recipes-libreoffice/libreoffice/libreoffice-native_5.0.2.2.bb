require ${BPN}.inc

inherit native

DEPENDS += " \
    curl-native \
    boost-native \
    icu-native \
    expat-native \
"

EXTRA_OECONF += " \
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

do_compile() {
    BUILDDIR=${B} oe_runmake -f ${S}/Makefile.gbuild build-tools
}

#LOBUILDLIBS = "libtllo.so  libuno_sal.so.3"

do_install() {
    install -d ${D}/${bindir}
    for name in ${LOBUILDTOOLS} ; do
        install "${B}/workdir/LinkTarget/Executable/$name" ${D}/${bindir}
    done

    # icu creates a gendict to avoid conflicts rename in sysroot
    install "${B}/workdir/LinkTarget/Executable/gendict" ${D}/${bindir}/gendict_libre

    # install sdk binaries
    install ${B}/instdir/sdk/bin/* ${D}/${bindir}

    install -d ${D}/${libdir}
    for name in `find ${B}/instdir/program -name *.so*` ; do
        install "$name" ${D}/${libdir}
    done

#    for name in ${LOBUILDLIBS} ; do
#        install "${B}/instdir/program/$name" ${D}/${libdir}
#    done
}
