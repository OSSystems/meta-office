SUMMARY = "C++ client library for the CMIS interface"
HOMEPAGE = "https://github.com/tdf/libcmis"
LICENSE = "MPL-1.1 & GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=0117647fecb9a932c25a7bbfc0333c37 \
    file://COPYING.LGPL;md5=4fbd65380cdd255951079008b364516c \
    file://COPYING.GPL;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
"

SRC_URI = " \
    https://github.com/tdf/libcmis/releases/download/v${PV}/${BPN}-${PV}.tar.gz \
    file://0001-Avoid-cross-compile-unsafe-paths.patch \
"

SRC_URI[md5sum] = "3270154f0f40d86fce849b161f914101"
SRC_URI[sha256sum] = "6acbdf22ecdbaba37728729b75bfc085ee5a4b49a6024757cfb86ccd3da27b0e"

inherit autotools pkgconfig

DEPENDS = "curl libxml2 cppunit boost"

EXTRA_OECONF = " \
    --without-man \
"

do_configure_prepend() {
    touch ${S}/README
}

BBCLASSEXTEND = "native"
