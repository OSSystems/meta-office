DESCRIPTION = "Libre office base"
LICENSE = "GPLv3 LGPLv3 MPLv1.1"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
    file://COPYING.LGPL;md5=6a6a8e020838b23406c81b19c1d46df6 \
    file://COPYING.MPL;md5=bfe1f75d606912a4111c90743d6c7325 \
"

SRC_URI = " \
    http://download.documentfoundation.org/libreoffice/src/4.1.0/libreoffice-${PV}.tar.xz \
    file://0001-configure.ac-remove-cross-compile-section-ist-does-n.patch \
    file://0002-configure.ac-remove-special-cross-code-for-Pyuno.patch \
"
SRC_URI[md5sum] = "fd49e3518a91761dc9f7129476e60f19"
SRC_URI[sha256sum] = "38daadf5da5f69962b12a9e4d36ad830d122ebab505aa6be19ca8ed780a8f776"

inherit autotools pkgconfig perlnative distutils-base

DEPENDS = "python-lxml-native ccache-native archive-zip-native gperf-native bison-native"
DEPENDS += "gtk+ gconf virtual/libx11 gst-plugins-base libxt openssl cups zlib fontconfig libxml2 libxslt"

# necessary to let the call for python-config succeed
export BUILD_SYS
export HOST_SYS
export STAGING_LIBDIR
export STAGING_INCDIR

# revisit: once we have python >= 3.3 --disable-python can go
EXTRA_OECONF = " \
    --with-gcc-home=${TOOLCHAIN_PATH} \
    --without-java \
    --without-doxygen \
    --disable-python \
    --disable-postgresql-sdbc \
    --disable-opengl \
"

# for scripting (requires python >= 3.3)
#RDEPENDS_${PN} = "python"
