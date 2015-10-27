SUMMARY = "Library providing the RDF API and triple stores"
LICENSE = "GPLv2 | LGPLv2"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=751419260aa954499f7abaabaa882bbe \
    file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1 \
"

DEPENDS = "rasqal mariadb libtool"

SRC_URI = "http://download.librdf.org/source/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "e5be03eda13ef68aabab6e42aa67715e"
SRC_URI[sha256sum] = "de1847f7b59021c16bdc72abb4d8e2d9187cd6124d69156f3326dd34ee043681"

inherit autotools gtk-doc
