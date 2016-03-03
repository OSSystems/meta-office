SUMMARY = "Font rendering capabilities for complex non-Roman writing systems"
HOMEPAGE = "http://sourceforge.net/projects/silgraphite"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=acba2ba259d936c324b90ab679e6b901"

inherit cmake

DEPENDS += "freetype"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/silgraphite/${BPN}/${BPN}-${PV}.tgz"
SRC_URI[md5sum] = "5b8d22a8bbf031838e31432868c0109c"
SRC_URI[sha256sum] = "f4712626eda0a1804367133460770560b545bbe75b1c4aab480c9b8e4e1c64bd"
