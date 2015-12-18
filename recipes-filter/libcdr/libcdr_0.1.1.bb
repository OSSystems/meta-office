SUMMARY = "CorelDraw file format importer library for LibreOffice"
HOMEPAGE = "https://wiki.documentfoundation.org/DLP/Libraries/libcdr"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
"

SRC_URI = "http://dev-www.libreoffice.org/src/${BPN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "b33fd0be3befdd1b37777e08ce058bd9"
SRC_URI[sha256sum] = "72fe7bbbf2275242acdf67ad2f9b6c71ac9146a56c409def360dabcac5695b49"

inherit autotools pkgconfig

DEPENDS = "icu lcms librevenge"

BBCLASSEXTEND = "native"
