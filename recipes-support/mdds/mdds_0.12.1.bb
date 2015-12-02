SUMMARY = "A collection of multi-dimensional data structures and indexing algorithms"
HOMEPAGE = "https://gitlab.com/mdds/mdds"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=7371ccd26e1d800140423a16d724333e"

inherit autotools-brokensep pkgconfig

SRC_URI = "http://kohei.us/files/${BPN}/src/${BPN}_${PV}.tar.bz2"
SRC_URI[md5sum] = "ef2560ed5416652a7fe195305b14cebe"
SRC_URI[sha256sum] = "23565e9d7810a6ac30478833813db847f80e927b414a7be07b7cc03ed3aae83d"

S = "${WORKDIR}/${BPN}_${PV}"

DEPENDS = "boost"

BBCLASSEXTEND = "native"
