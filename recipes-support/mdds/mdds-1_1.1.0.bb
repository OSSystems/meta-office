SUMMARY = "A collection of multi-dimensional data structures and indexing algorithms"
HOMEPAGE = "https://gitlab.com/mdds/mdds"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=60a6093677ded88b5e28677e52a0c011"

inherit autotools-brokensep pkgconfig

SRC_URI = " \
    http://kohei.us/files/mdds/src/mdds-${PV}.tar.bz2 \
    file://0001-configure.ac-remove-fixed-paths-causing-trouble-when.patch \
"
SRC_URI[md5sum] = "c300541adac09008aa4a305eacd1dca6"
SRC_URI[sha256sum] = "4253ab93fe8bb579321a50e247f1f800191ab99fe2d8c6c181741b8bd3fb161f"

S = "${WORKDIR}/mdds-${PV}"

DEPENDS = "boost"

BBCLASSEXTEND = "native"
