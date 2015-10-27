SUMMARY = "Library for querying RDF"
LICENSE = "GPLv2 | LGPLv2"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=751419260aa954499f7abaabaa882bbe \
    file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1 \
"

DEPENDS = "raptor2 util-linux mpfr"

SRC_URI = "http://download.librdf.org/source/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "1f5def51ca0026cd192958ef07228b52"
SRC_URI[sha256sum] = "6924c9ac6570bd241a9669f83b467c728a322470bf34f4b2da4f69492ccfd97c"

inherit autotools gtk-doc
