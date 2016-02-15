SUMMARY = "VIGRA is an image processing and analysis library"
HOMEPAGE = "http://ukoethe.github.io/vigra/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://LICENSE.txt;md5=3c8d5650f165c9d2bf413c64aa33dc15 \
"

SRC_URI = "git://github.com/ukoethe/vigra.git"
SRCREV = "e6c21f3506d783242222c44b2f4f4ec55afdab01"
S = "${WORKDIR}/git"

inherit cmake

DEPENDS = "tiff jpeg libpng"

EXTRA_OECMAKE += "-DWITH_VIGRANUMPY=0"

do_install_append() {
    # doc does not have useful content and is installed at the wrong location
    # so delete it
    rm -rf "${D}/${prefix}/doc"
}

BBCLASSEXTEND = "native"
