SUMMARY = "Import filter library for MS Works"
HOMEPAGE = "https://sourceforge.net/projects/libwps/"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
"

SRC_URI = "http://dev-www.libreoffice.org/src/${BPN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "8a6c55542ce80203dd6d3b1cba99d4e5"
SRC_URI[sha256sum] = "254b8aeb36a3b58eabf682b04a5a6cf9b01267e762c7dc57d4533b95f30dc587"

inherit autotools pkgconfig

DEPENDS = "librevenge"
