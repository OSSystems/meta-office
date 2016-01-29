require ${BPN}.inc

inherit gtk-icon-cache

SRC_URI += " \
    file://0002-configure.ac-skip-some-cross-compile-sections-they-d.patch \
    file://0003-Makefile.in-avoid-building-target-cross-toolset.patch \
    file://0004-remove-paths-for-gb_Executable_get_command.patch \
    file://0005-ensure-that-native-gendict-build-by-libreoffice-is-u.patch \
    file://0009-add-a-new-gb_Rdb_get_target_for_build_native-and-use.patch \
    file://0010-make-sure-that-gengal-uses-native-libraries.patch \
    file://0014-Package.mk-workaround-icu-missing-error-for-without-.patch \
    file://0015-configure.ac-avoid-finding-calling-pg_config.patch \
    file://0016-configure.ac-don-t-check-locations-apache-common-hsq.patch \
    file://0017-configure.ac-replace-non-working-perl-version-check-.patch \
"

# Note hsqldb: currently libreoffice wants a hsqldb 1.8.0.x - see configure.ac.
# While writing meta-java ships 1.8.0.10 but the perl version checking in 
# configure.ac does not detect it as valid. To work around and keep track of
# hsqldb version in meta-java we
# * hardcode hsqldb version in PACKAGECONFIG[java] see below
# * patch configure.ac (0017) if the hardcoded file is there

DEPENDS += " \
    ${BPN}-native \
    \
    curl \
    boost \
    icu \
    expat \
    poppler \
    harfbuzz \
    openldap \
    nss \
    zlib \
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
    openssl \
    cups \
    gstreamer1.0-plugins-base \
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
    libexttextcat \
    clucene-core \
    vigra \
    hunspell \
    mythes \
    hyphen \
"

# necessary to let the call for python-config succeed
export BUILD_SYS
export HOST_SYS
export STAGING_LIBDIR
export STAGING_INCDIR

# Notes:
#
# 1. By default many many sources are downloaded from libreoffice mirrors.
# This can be avoided by --with-system-.. To see what's still loaded check
# log.do_compile.
#
# 2. problems during configure detected for (TBD?)
# * boost: 'configure: error: Could not find a version of the library!'
#
# 3. in case of trouble in do_compile: configure with --enable-verbose might
# help detecting culprit
#
# 4. TODO --with-parallelism
# 5. galleries fail to build / prebuild from external sources?

EXTRA_OECONF += " \
    --enable-verbose \
    \
    --disable-collada \
    --disable-coinmp \
    --enable-python=system \
    --with-tls=nss \
    --without-galleries \
    \
    --with-system-poppler \
    --with-system-openldap \
    --with-system-zlib \
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
    --with-system-glew \
    --with-system-openssl \
    \
    --with-system-cppunit \
    --with-system-glm \
    --with-system-mdds \
    --with-system-redland \
    --with-system-libabw \
    --with-system-libwps \
    --with-system-libwpg \
    --with-system-libwpd \
    --with-system-libcdr \
    --with-system-libcmis \
    --with-system-libebook \
    --with-system-libmwaw \
    --with-system-libetonyek \
    --with-system-libvisio \
    --with-system-libmspub \
    --with-system-libpagemaker \
    --with-system-libodfgen \
    --with-system-libgltf \
    --with-system-libexttextcat \
    --with-system-clucene \
    --with-system-vigra \
    --with-system-hunspell \
    --with-system-mythes \
    --with-system-altlinuxhyph \
"

PACKAGECONFIG ??= " \
    gtk \
    mariadb \
    postgresql \
    ${@bb.utils.contains('BBFILE_COLLECTIONS', 'meta-java', 'java', '', d)} \
"

PACKAGECONFIG[gtk] = "--enable-gtk , --disable-gtk, gtk+ cairo"
PACKAGECONFIG[gtk3] = "--enable-gtk3 , --disable-gtk3, gtk+3 cairo"

PACKAGECONFIG[mariadb] = "--enable-ext-mariadb-connector --enable-bundle-mariadb --with-system-mariadb, --disable-ext-mariadb-connector --disable-bundle-mariadb, mariadb"
PACKAGECONFIG[postgresql] = "--enable-postgresql-sdbc --with-system-postgresql, --disable-postgresql-sdbc, postgresql"

JDK_CROSS = "openjdk-8"
JAR_NATIVE = "${STAGING_DATADIR_NATIVE}/java"
PACKAGECONFIG[java] = " \
    --with-jdk-home=${STAGING_LIBDIR_NATIVE}/jvm/${JDK_CROSS}-native --with-ant-home=${JAR_NATIVE} --with-junit=no \
    --enable-scripting-javascript \
    --with-system-hsqldb --with-hsqldb-jar=${STAGING_DATADIR}/java/hsqldb-1.8.0.10.jar \
    --with-system-apache-commons \
    --with-commons-codec-jar=${STAGING_DATADIR}/java/commons-codec.jar --with-commons-httpclient-jar=${STAGING_DATADIR}/java/commons-httpclient.jar \
    --with-commons-logging-jar=${STAGING_DATADIR}/java/commons-logging.jar --with-commons-lang-jar=${STAGING_DATADIR}/java/commons-lang.jar \
    , \
    --without-java \
    , \
    ${JDK_CROSS} hsqldb rhino commons-codec commons-httpclient commons-logging commons-lang \
"

do_configure() {
    olddir=`pwd`
    cd ${S}
    aclocal --system-acdir=${B}/aclocal-copy/ -I ${S}/m4
    gnu-configize
    autoconf
    cd $olddir

    # we need to help configuration to find platform specific libjawt
    # by default it would search in native sysroot
    if [ -d ${STAGING_LIBDIR}/jvm/${JDK_CROSS}/lib ]; then
        JAWTDIR=`find ${STAGING_LIBDIR}/jvm/${JDK_CROSS}/lib -name libjawt.so`
        JAWTDIR=`dirname $JAWTDIR`
        export JAWTLIB="-L$JAWTDIR -ljawt"
        echo "JAWTLIB=$JAWTLIB"
    fi
    oe_runconf

    mkdir -p ${B}/workdir/Executable

    # icu binaries are expected in our build tree
    mkdir -p ${B}/workdir/UnpackedTarball/icu/source/
    cd ${B}/workdir/UnpackedTarball/icu/source/
    ln -sf ${STAGING_DATADIR_NATIVE}/icu/55.1/bin

    # link to native saxparser.rdb - cross version of that file is useless
    sed -i 's:%STAGING_LIBDIR_NATIVE%:${STAGING_LIBDIR_NATIVE}:g' ${S}/solenv/gbuild/TargetLocations.mk

    # ensure gengal loads native libraries
    sed -i 's:%STAGING_LIBDIR_NATIVE%:${STAGING_LIBDIR_NATIVE}:g' ${S}/solenv/gbuild/Gallery.mk
}

do_install() {
    make DESTDIR=${D} distro-pack-install
}


FILES_${PN} += " \
    ${datadir}/mime \
    ${datadir}/application-registry \
    ${datadir}/mimelnk \
    ${datadir}/icons \
    ${datadir}/appdata \
    ${datadir}/mime-info \
    ${datadir}/mime/packages \
    \
"

PACKAGE_BEFORE_PN += "${PN}-sdk"
FILES_${PN}-sdk = " \
    ${libexecdir}/sdk \
"
INSANE_SKIP_${PN}-sdk += "dev-so staticdev"

FILES_${PN}-dbg += " \
    ${libexecdir}/*/.debug \
    ${libexecdir}/*/*/.debug \
    ${libexecdir}/*/*/*/.debug \
"
