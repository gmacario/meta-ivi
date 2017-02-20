SUMMARY = "GENIVI Persistence Administration Service"

HOMEPAGE = "https://at.projects.genivi.org/wiki/display/PROJ/GENIVI+Persistence+Management"
SECTION = "base"

LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

SRCREV = "7fb70fdc6b42047024afbab1598424236e822985"
SRC_URI = " \
    git://github.com/GENIVI/persistence-administrator.git;protocol=https \
    "
S = "${WORKDIR}/git"

DEPENDS = "glib-2.0 dbus dlt-daemon libarchive zlib json-c node-state-manager \
           systemd persistence-common-object libffi"

inherit autotools-brokensep systemd pkgconfig

FILES_${PN}-dev += "${datadir}/dbus-1/"

PR = "r1"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "pas-daemon.service"
SYSTEMD_AUTO_ENABLE = "disable"

do_configure_prepend() {
   ln -sf README.md README
}

do_install_append() {
   perl -pi -e 's/dbus-public-bus.service/dbus.service/' \
	${D}/lib/systemd/system/pas-daemon.service
   rm -f ${D}${bindir}/pers_admin_test_framework
}
