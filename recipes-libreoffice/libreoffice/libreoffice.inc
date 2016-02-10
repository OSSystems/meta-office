DESCRIPTION = "Libre office base"
LICENSE = "GPLv3 & LGPLv3 & MPLv1.1"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
    file://COPYING.LGPL;md5=6a6a8e020838b23406c81b19c1d46df6 \
    file://COPYING.MPL;md5=9741c346eef56131163e13b9db1241b3 \
"

require libreoffice-version.inc

SRC_URI += " \
    file://0001-update-ax_boost-scripts-to-fix-configure-with-system.patch \
"

inherit autotools-brokensep pkgconfig distutils-base perlnative

DEPENDS = "python3-lxml-native ccache-native archive-zip-native gperf-native bison-native"

EXTRA_OECONF = " \
    --without-doxygen \
    --enable-release-build \
    \
    --with-system-librevenge \
    --with-system-libabw \
    --with-system-libcdr \
    --with-system-libebook \
    --with-system-libfreehand \
"
