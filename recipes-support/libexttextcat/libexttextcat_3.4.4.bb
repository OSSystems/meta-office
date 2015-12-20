SUMMARY = "N-Gram-Based Text Categorization library for language guessing"
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/libexttextcat"
LICENSE = "BSD"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=873b1664864cb88b5f5b4eca62deb23c \
"

SRC_URI = "http://dev-www.libreoffice.org/src/${BPN}/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "bfa7107c27afda3a3afa4b7ab5a3fe17"
SRC_URI[sha256sum] = "f24c086cf3523424228ed58b9f678cc7647688822e5407d5fbc155fbfc846293"

inherit autotools pkgconfig

FILES_${PN} += "${datadir}/vala"

BBCLASSEXTEND = "native"
