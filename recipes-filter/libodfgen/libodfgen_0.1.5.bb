SUMMARY = "An ODF generator library"
HOMEPAGE = "https://sourceforge.net/p/libwpd/wiki/libodfgen"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/libwpd/${BPN}/${BPN}-${PV}/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "e0a76e8fb84a3aceb70bdb733c7431ac"
SRC_URI[sha256sum] = "fb8706e38f6acf019f8b50262af2bf850821c6d10dad8708f13e54f1a08294dd"

inherit autotools pkgconfig perlnative

DEPENDS = "librevenge"
