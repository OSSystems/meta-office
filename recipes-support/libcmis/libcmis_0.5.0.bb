SUMMARY = "C++ client library for the CMIS interface"
HOMEPAGE = "http://sourceforge.net/projects/libcmis/"
LICENSE = "MPL-1.1 & GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=0117647fecb9a932c25a7bbfc0333c37 \
    file://COPYING.LGPL;md5=4fbd65380cdd255951079008b364516c \
    file://COPYING.GPL;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
"

SRC_URI = " \
    http://downloads.sourceforge.net/project/${BPN}/${BPN}-${PV}.tar.gz \
    file://0001-fix-boost-configuration-with-gcc-5.patch \
"
SRC_URI[md5sum] = "5821b806a98e6c38370970e682ce76e8"
SRC_URI[sha256sum] = "a87e02913dee3ee659db5abf6d7dafcfcd85dd4b24bf4389d8d4afe8c8dcf9b6"

inherit autotools pkgconfig

DEPENDS = "curl libxml2 cppunit boost"

EXTRA_OECONF = " \
    --without-man \
"

BBCLASSEXTEND = "native"
