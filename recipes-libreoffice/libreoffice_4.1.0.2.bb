DESCRIPTION = "Libre office base"
LICENSE = "GPLv3 LGPLv3 MPLv1.1"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
    http://download.documentfoundation.org/libreoffice/src/4.1.0/libreoffice-${PV}.tar.xz \
    file://0001-configure.ac-remove-cross-compile-section-ist-does-n.patch \
"
SRC_URI[md5sum] = "fd49e3518a91761dc9f7129476e60f19"
SRC_URI[sha256sum] = "38daadf5da5f69962b12a9e4d36ad830d122ebab505aa6be19ca8ed780a8f776"

inherit autotools perlnative

DEPENDS = "cups ccache-native"

EXTRA_OECONF = " \
	--with-gcc-home=${TOOLCHAIN_PATH} \
"

#do_configure_prepend() {
#	export CC="${CC}"
#}
