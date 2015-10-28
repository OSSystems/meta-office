SUMMARY = "Library that parses the file format of AbiWord documents"
HOMEPAGE = "https://wiki.documentfoundation.org/DLP/Libraries/libabw"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
"

SRC_URI = "http://dev-www.libreoffice.org/src/${BPN}/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "6be55a46078b593ec20cad9bb8730c3e"
SRC_URI[sha256sum] = "cfc698c2dd4ab592138a941ae26e2e18c3dac69bc196b5167df7f5eb88fc217e"

inherit autotools pkgconfig

DEPENDS = "libxml2 librevenge"
