require ${BPN}.inc

SRC_URI += " \
    file://0002-configure.ac-skip-some-cross-compile-sections-they-d.patch \
    file://0003-Makefile.in-avoid-building-target-cross-toolset.patch \
    file://0004-avoid-calling-cross-build-cppumaker.patch \
"

DEPENDS += " \
    ${BPN}-native \
    gtk+3 \
    curl \
    boost \
    icu \
    expat \
    poppler \
    harfbuzz \
    openldap \
    nss \
    zlib \
    mariadb \
    jpeg \
    neon \
    libpng \
    apr \
    serf \
    libatomic-ops \
    lcms \
    harfbuzz \
    cppunit \
    glew \
    \
    mdds \
    glm \
    redland \
    libabw \
    libwps \
    libwpg \
    libwpd \
    libcdr \
    librevenge \
    libcmis \
    libfreehand \
    libe-book \
    libmwaw \
    libetonyek \
    libvisio \
    libmspub \
    libpagemaker \
    libodfgen \
    libgltf \
"

# necessary to let the call for python-config succeed
export BUILD_SYS
export HOST_SYS
export STAGING_LIBDIR
export STAGING_INCDIR

# By default many many sources are downloaded from libreoffice mirrors. 
# This can be avoided by --with-system-.. To see what's still loaded check
# log.do_compile.
# problems during configure detected for (TBD?)
# * boost: 'configure: error: Could not find a version of the library!'
EXTRA_OECONF += " \
    --enable-gtk3 \
    --disable-postgresql-sdbc \
    --disable-collada \
    --disable-coinmp \
    --enable-python=system \
    --with-tls=nss \
    --with-system-poppler \
    --with-system-openldap \
    --with-system-zlib \
    --with-system-mariadb \
    --with-system-jpeg \
    --with-system-neon \
    --with-system-libpng \
    --with-system-nss \
    --with-system-apr \
    --with-system-serf \
    --with-system-libatomic_ops \
    --with-system-lcms2 \
    --with-system-libxml \
    --with-system-icu \
    --with-system-expat \
    --with-system-curl \
    --with-system-harfbuzz \
    --with-system-cppunit \
    --with-system-glew \
    \
    --with-system-glm \
    --with-system-mdds \
    --with-system-redland \
    --with-system-libabw \
    --with-system-libwps \
    --with-system-libwpg \
    --with-system-libwpd \
    --with-system-libcdr \
    --with-system-librevenge \
    --with-system-libcmis \
    --with-system-libfreehand \
    --with-system-libebook \
    --with-system-libmwaw \
    --with-system-libetonyek \
    --with-system-libvisio \
    --with-system-libmspub \
    --with-system-libpagemaker \
    --with-system-libodfgen \
    --with-system-libgltf \
"

do_configure() {
    olddir=`pwd`
    cd ${S}
    aclocal --system-acdir=${B}/aclocal-copy/ -I ${S}/m4
    gnu-configize
    autoconf
    cd $olddir
    oe_runconf

    mkdir -p ${B}/workdir/Executable

    # native binaries are expected in ${B}/workdir/LinkTarget/Executable
    mkdir -p ${B}/workdir/LinkTarget/Executable
    cd ${B}/workdir/LinkTarget/Executable
    for name in ${LOBUILDTOOLS} ; do
        ln -sf ${STAGING_BINDIR_NATIVE}/$name
    done

    # see libreoffice-native do_install
    ln -sf ${STAGING_BINDIR_NATIVE}/gendict_libre gendict

    # icu binaries ar expected in our build tree
    mkdir -p ${B}/workdir/UnpackedTarball/icu/source/
    cd ${B}/workdir/UnpackedTarball/icu/source/
    ln -sf ${STAGING_DATADIR_NATIVE}/icu/55.1/bin
}

# for scripting (requires python >= 3.3)
#RDEPENDS_${PN} = "python3"
