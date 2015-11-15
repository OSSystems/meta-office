SUMMARY = "A library for import of Microsoft Publisher documents"
HOMEPAGE = "http://wiki.documentfoundation.org/DLP/Libraries/libmspub"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
"
SRC_URI = "http://dev-www.libreoffice.org/src/${BPN}/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "050b8b29c2620fdbfb5d8561d4d08a11"
SRC_URI[sha256sum] = "b0baabf82d20c08ad000e80fa02154ce2f2ffde1ee60240d6e3a917c3b35560f"

inherit autotools pkgconfig

DEPENDS = "icu librevenge"
