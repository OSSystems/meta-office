SUMMARY = "A library for import of Microsoft Visio diagrams"
HOMEPAGE = "http://wiki.documentfoundation.org/DLP/Libraries/libvisio"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
"
SRC_URI = "http://dev-www.libreoffice.org/src/${BPN}/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "fda521a18bbfccc5f034ea02119e7998"
SRC_URI[sha256sum] = "943e03b1e6c969af4c2133a6671c9630adf3aaf8d460156744a28f58c9f47cd8"

inherit autotools pkgconfig

DEPENDS = "icu libxml2 librevenge"
