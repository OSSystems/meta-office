SUMMARY = "A library for import of Adobe PageMaker documents"
HOMEPAGE = "http://wiki.documentfoundation.org/DLP/Libraries/libpagemaker"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=815ca599c9df247a0c7f619bab123dad \
"
SRC_URI = "http://dev-www.libreoffice.org/src/${BPN}/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "1e6fec3e30f4f47c4c2619ee232dec04"
SRC_URI[sha256sum] = "cdbdf86605773339caab6477ff694a95a90aaa4d45bb6cdb59e4a7f76c91ef17"

inherit autotools pkgconfig

DEPENDS = "icu librevenge"
