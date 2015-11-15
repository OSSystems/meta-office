SUMMARY = "A library for import of many old Mac document formats"
HOMEPAGE = "http://sourceforge.net/projects/libmwaw"
LICENSE = " LGPLv2.1 & MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.LGPL;md5=a049c5e22d3bd7bc3c9a2e9135a6d104 \
    file://COPYING.MPL;md5=cce0d89a18de69e7f51f693182ac4a3e \
"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/${BPN}/${BPN}/${BPN}-${PV}/${BPN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "a8364bf2e4ece2860ab96d6bee75f1d7"
SRC_URI[sha256sum] = "fb3fcac71eefe4d80ea76c08d259109bb020836cbd9f8d73f06daed1801a637c"

inherit autotools pkgconfig

DEPENDS = "librevenge"
