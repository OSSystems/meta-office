SUMMARY = "A library for import of import of Apple iWork documents"
HOMEPAGE = "http://wiki.documentfoundation.org/DLP/Libraries/libetonyek"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=815ca599c9df247a0c7f619bab123dad \
"
SRC_URI = "http://dev-www.libreoffice.org/src/${BPN}/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "8a2c7de34b5c02f4a75ba086bc5af57b"
SRC_URI[sha256sum] = "fe12276a62bd5f5ca4f5bfbd4938a74d097084e1f9fe173e521f63203f56f055"

inherit autotools pkgconfig

DEPENDS = "glm mdds icu lcms libxml2 librevenge"

BBCLASSEXTEND = "native"
