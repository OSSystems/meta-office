DESCRIPTION = "Libre office base"
LICENSE = "GPLv3 & LGPLv3 & MPLv1.1"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
    file://COPYING.LGPL;md5=6a6a8e020838b23406c81b19c1d46df6 \
    file://COPYING.MPL;md5=9741c346eef56131163e13b9db1241b3 \
"

PV = "5.0.3.2"

SRC_URI = " \
    http://download.documentfoundation.org/libreoffice/src/5.0.3/libreoffice-${PV}.tar.xz \
    file://0001-update-ax_boost-scripts-to-fix-configure-with-system.patch \
"
SRC_URI[md5sum] = "879471fa970c30bb080e11906a3bcdab"
SRC_URI[sha256sum] = "77f9c9569c1199d914f38afa01547c1874535c56ad74b19614a0afc3ad66d4bd"

inherit autotools pkgconfig distutils-base perlnative

DEPENDS = "python3-lxml-native ccache-native archive-zip-native gperf-native bison-native"

LOBUILDTOOLS = " \
    bestreversemap \
    cpp \
    gencoll_rule \
    genconv_dict \
    genindex_data \
    rsc \
    svidl \
    transex3 \
    unoidl-check \
    unoidl-write \
"

EXTRA_OECONF = " \
    --without-java \
    --without-doxygen \
"
