SUMMARY = "A library for rendering glTF models"
HOMEPAGE = "https://wiki.documentfoundation.org/Development/libgltf"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=9741c346eef56131163e13b9db1241b3 \
"
SRC_URI = "http://dev-www.libreoffice.org/src/${BPN}/${BPN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "d63a9f47ab048f5009d90693d6aa6424"
SRC_URI[sha256sum] = "d1cc7297ed1921aa969e26413b4c4e18afc882ce4d2f5a2aa2a2905706f7206b"

inherit autotools pkgconfig

DEPENDS = "glew glm boost"
