SUMMARY = "Library for importing and converting Corel WordPerfect(tm) graphics"
HOMEPAGE = "http://libwpg.sourceforge.net/"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
"

SRC_URI = "http://dev-www.libreoffice.org/src/${BPN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "17da9770cb8b317b7633f9807b32b71a"
SRC_URI[sha256sum] = "28fc3580228a82948dfc01d07abd5076c8b0df76a68702c1a81eb88fdf377348"

inherit autotools pkgconfig

DEPENDS = "librevenge libwpd zlib"
RDEPENDS_${PN} = "perl"
