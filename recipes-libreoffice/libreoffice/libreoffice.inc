DESCRIPTION = "Libre office base"
LICENSE = "GPLv3 & LGPLv3 & MPLv1.1"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
    file://COPYING.LGPL;md5=6a6a8e020838b23406c81b19c1d46df6 \
    file://COPYING.MPL;md5=9741c346eef56131163e13b9db1241b3 \
"

PV = "5.0.4.2"

SRC_URI = " \
    http://download.documentfoundation.org/libreoffice/src/5.0.4/libreoffice-${PV}.tar.xz \
    file://0001-update-ax_boost-scripts-to-fix-configure-with-system.patch \
"
SRC_URI[md5sum] = "0c6381581f93ef7142b00837002755dd"
SRC_URI[sha256sum] = "647440b70fc190a43a3373cfe5b06a87558ee07711e3d74cab8fb9d8cbac6dc8"

inherit autotools-brokensep pkgconfig distutils-base perlnative

DEPENDS = "python3-lxml-native ccache-native archive-zip-native gperf-native bison-native"

LOBUILDTOOLS = " \
    bestreversemap \
    cpp \
    gencoll_rule \
    genconv_dict \
    genindex_data \
    rsc \
    saxparser \
    svidl \
    transex3 \
    ulfex \
    unoidl-check \
    unoidl-write \
"

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
