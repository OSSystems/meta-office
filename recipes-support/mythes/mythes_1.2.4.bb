SUMMARY = "A thesaurus library"
HOMEPAGE = "http://hunspell.sourceforge.net/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=b128e548f03bd893acff0bbd9053aaef \
"


SRC_URI = "${SOURCEFORGE_MIRROR}/project/hunspell/MyThes/${PV}/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "a8c2c5b8f09e7ede322d5c602ff6a4b6"
SRC_URI[sha256sum] = "1e81f395d8c851c3e4e75b568e20fa2fa549354e75ab397f9de4b0e0790a305f"

inherit autotools pkgconfig

DEPENDS = "hunspell"

do_configure_prepend() {
    touch ${S}/NEWS
}

RDEPENDS_${PN} = "perl"

BBCLASSEXTEND = "native"
