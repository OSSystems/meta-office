DESCRIPTION = "Libre office base"
LICENSE = "GPLv3 & LGPLv3 & MPLv1.1"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
    file://COPYING.LGPL;md5=6a6a8e020838b23406c81b19c1d46df6 \
    file://COPYING.MPL;md5=9741c346eef56131163e13b9db1241b3 \
"

SRC_URI = " \
    http://download.documentfoundation.org/libreoffice/src/5.0.2/libreoffice-${PV}.tar.xz \
    file://0003-update-ax_boost-scripts-to-fix-configure-with-system.patch \
"
SRC_URI[md5sum] = "e3d129dd7a579b14984c9c1231e851a5"
SRC_URI[sha256sum] = "01f0680709432039762fa608bcb1240786d2b6549099a96095aab92dcebbc176"

inherit autotools pkgconfig distutils-base perlnative pythonnative

DEPENDS = "python3-lxml-native ccache-native archive-zip-native gperf-native bison-native"

LOBUILDTOOLS = " \
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
