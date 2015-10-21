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
#DEPENDS += "gtk+ gconf virtual/libx11 gst-plugins-base libxt openssl cups zlib fontconfig libxml2 libxslt"
DEPENDS = "poppler"

# necessary to let the call for python-config succeed
export BUILD_SYS
export HOST_SYS
export STAGING_LIBDIR
export STAGING_INCDIR

EXTRA_AUTORECONF = "--exclude=autoheader"

EXTRA_OECONF = " \
    --without-java \
    --without-doxygen \
    --disable-postgresql-sdbc \
    --enable-python=internal \
    --with-system-poppler \
"

do_configure_append() {
    mkdir -p ${B}/workdir/Executable
}

# for scripting (requires python >= 3.3)
#RDEPENDS_${PN} = "python3"
