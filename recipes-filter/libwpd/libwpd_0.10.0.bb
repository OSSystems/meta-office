SUMMARY = "Library for importing WordPerfect (tm) documents"
HOMEPAGE = "http://libwpd.sourceforge.net/"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
"

SRC_URI = "http://dev-www.libreoffice.org/src/${BPN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "0773d79a1f240ef9f4f20242b13c5bb7"
SRC_URI[sha256sum] = "f2bf5d65156a351ce404550dd822c8db8ab8740b393f61dba828d1b2cb33fe91"

inherit autotools pkgconfig perlnative

DEPENDS = "librevenge"
