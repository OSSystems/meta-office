SUMMARY = "library for REVerses ENGineered formats filters"
HOMEPAGE = "http://sf.net/p/libwpd/librevenge/"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
"

SRC_URI = "http://dev-www.libreoffice.org/src/${BPN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "2d4183bf17aea1a71842468a71a68c47"
SRC_URI[sha256sum] = "dedd6fe1f643fc2f254f2ad3719547084bd86bcc482104b995caf3b828368b18"

inherit autotools pkgconfig

DEPENDS = "cppunit zlib boost"

BBCLASSEXTEND = "native"

do_install_append() {
    sed -i '/^Libs:/ s/$/ -lboost_system/' ${D}${libdir}/pkgconfig/librevenge-0.0.pc
}
