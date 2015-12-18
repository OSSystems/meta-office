SUMMARY = "A library for import of Macromedia/Adobe FreeHand documents"
HOMEPAGE = "http://wiki.documentfoundation.org/DLP/Libraries/libfreehand"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=815ca599c9df247a0c7f619bab123dad \
"
SRC_URI = "http://dev-www.libreoffice.org/src/${BPN}/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "97eb6a8607de3c101ad05ceacc122107"
SRC_URI[sha256sum] = "ec6676d0c63f7feac7801a1fe18dd7abe9044b39c3882fc99b9afef39bdf1d30"

inherit autotools pkgconfig

DEPENDS = "icu lcms librevenge"

BBCLASSEXTEND = "native"
