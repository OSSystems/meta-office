SUMMARY = "A library for import of reflowable e-book formats"
HOMEPAGE = "http://sourceforge.net/projects/libebook"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=815ca599c9df247a0c7f619bab123dad \
"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/libebook/${BPN}-${PV}/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "19d84f4a97aab32d350d1f47ea3da0b3"
SRC_URI[sha256sum] = "7f894b1538b71c6cd96c976069c4dadb38b623612f0e35b2f6ee8a2c46bb88ec"

inherit autotools pkgconfig

DEPENDS = "icu libxml2 librevenge boost-native"

BBCLASSEXTEND = "native"
