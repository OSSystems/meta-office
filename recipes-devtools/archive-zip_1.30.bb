DESCRIPTION = "perl interface to ZIP archive files"
SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-1.0+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=385c55653886acac3821999a3ccd17b3"

SRC_URI = "http://www.cpan.org/authors/id/A/AD/ADAMK/Archive-Zip-${PV}.tar.gz"
SRC_URI[md5sum] = "40153666e7538b410e001aa8a810e702"
SRC_URI[sha256sum] = "f8b472ff77b7238e423bcb351968accc562f9d20700fbf2d8ed2a65fa0fa6318"

inherit cpan

DEPENDS = "zlib"

S = "${WORKDIR}/Archive-Zip-${PV}"

EXTRA_PERLFLAGS = "-I ${STAGING_LIBDIR_NATIVE}/perl-native/perl/${@get_perl_version(d)}"

BBCLASSEXTEND = "native"
