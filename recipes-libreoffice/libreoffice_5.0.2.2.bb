DESCRIPTION = "Libre office base"
LICENSE = "GPLv3 & LGPLv3 & MPLv1.1"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
    file://COPYING.LGPL;md5=6a6a8e020838b23406c81b19c1d46df6 \
    file://COPYING.MPL;md5=9741c346eef56131163e13b9db1241b3 \
"

SRC_URI = " \
    http://download.documentfoundation.org/libreoffice/src/5.0.2/libreoffice-${PV}.tar.xz \
    file://0001-configure.ac-skip-cross-compile-section-ist-does-not.patch \
"
SRC_URI[md5sum] = "e3d129dd7a579b14984c9c1231e851a5"
SRC_URI[sha256sum] = "01f0680709432039762fa608bcb1240786d2b6549099a96095aab92dcebbc176"

inherit autotools pkgconfig distutils-base perlnative pythonnative

DEPENDS = "python3-lxml-native ccache-native archive-zip-native gperf-native bison-native"
DEPENDS = "gtk+3 poppler harfbuzz expat curl openldap nss zlib mariadb jpeg neon libpng apr serf libatomic-ops lcms"

# necessary to let the call for python-config succeed
export BUILD_SYS
export HOST_SYS
export STAGING_LIBDIR
export STAGING_INCDIR

EXTRA_AUTORECONF = "--exclude=autoheader"

# By default many many sources are downloaded from libreoffice mirrors. 
# This can be avoided by --with-system-.. To see what's still loaded check
# log.do_compile.
# problems during configure detected for (TBD?)
# * boost: 'configure: error: Could not find a version of the library!'
# * glew: 'Requested 'glew >= 1.10.0' but version of glew is 1.9.0'
EXTRA_OECONF = " \
    --enable-gtk3 \
    --without-java \
    --without-doxygen \
    --disable-postgresql-sdbc \
    --enable-python=internal \
    --with-tls=nss \
    --with-system-poppler \
    --with-system-curl \
    --with-system-expat \
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
"

do_configure_append() {
    mkdir -p ${B}/workdir/Executable
}

# for scripting (requires python >= 3.3)
#RDEPENDS_${PN} = "python3"
