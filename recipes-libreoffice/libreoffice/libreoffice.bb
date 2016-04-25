require ${BPN}.inc

inherit gtk-icon-cache pythonnative

SRC_URI += " \
    http://download.documentfoundation.org/libreoffice/src/${DIRV}/${BPN}-translations-${PV}.tar.xz;name=translations \
    file://0001-configure.ac-skip-some-cross-compile-sections-they-d.patch \
    file://0002-Makefile.in-avoid-building-target-cross-toolset.patch \
    file://0003-remove-paths-for-gb_Executable_get_command.patch \
    file://0004-ensure-that-native-gendict-build-by-libreoffice-is-u.patch \
    file://0005-add-a-new-gb_Rdb_get_target_for_build_native-and-use.patch \
    file://0006-make-sure-that-gengal-uses-native-libraries.patch \
    file://0007-Package.mk-workaround-icu-missing-error-for-without-.patch \
    file://0008-configure.ac-avoid-finding-calling-pg_config.patch \
    file://0009-avoid-downloading-by-git-submodules.patch \
"

SRC_URI[translations.md5sum] = "9698d9d5948988a8f130d909bb0675ce"
SRC_URI[translations.sha256sum] = "67ad8bf35e55f3acb77a4fa9f1e07784a2acde72dcc9126567c833af70748b8d"

DEPENDS += " \
    ${BPN}-native \
    \
    curl \
    icu \
    expat \
    poppler \
    harfbuzz \
    openldap \
    nss \
    zlib \
    jpeg \
    neon \
    libpng \
    apr \
    serf \
    libatomic-ops \
    lcms \
    harfbuzz \
    cppunit \
    glew \
    openssl \
    cups \
    gstreamer1.0-plugins-base \
    \
    mdds \
    glm \
    redland \
    libabw \
    libwps \
    libwpg \
    libwpd \
    libcdr \
    librevenge \
    libcmis \
    libfreehand \
    libe-book \
    libmwaw \
    libetonyek \
    libvisio \
    libmspub \
    libpagemaker \
    libodfgen \
    libgltf \
    libexttextcat \
    clucene-core \
    vigra \
    hunspell \
    mythes \
    hyphen \
    graphite2 \
"

# necessary to let the call for python-config succeed
export BUILD_SYS
export HOST_SYS
export STAGING_LIBDIR
export STAGING_INCDIR

# Notes:
#
# 1. By default many many sources are downloaded from libreoffice mirrors.
# This can be avoided by --with-system-.. To see what's still loaded check
# log.do_compile.
#
# 2. problems during configure detected for (TBD?)
# * boost: 'configure: error: Could not find a version of the library!'
#
# 3. in case of trouble in do_compile: configure with --enable-verbose might
# help detecting culprit
#
# 5. --enable-scripting-javascript / rhino meta-java
# 6. Libreoffice Base embedded db / hsqldb meta-java
# 7. galleries fail to build / prebuild from external sources?

EXTRA_OECONF += " \
    --without-java \
    --with-lang=ALL \
    \
    --disable-collada \
    --disable-coinmp \
    --enable-python=system \
    --with-tls=nss \
    --without-galleries \
    \
    --with-system-poppler \
    --with-system-openldap \
    --with-system-zlib \
    --with-system-jpeg \
    --with-system-neon \
    --with-system-libpng \
    --with-system-nss \
    --with-system-apr \
    --with-system-serf \
    --with-system-libatomic_ops \
    --with-system-lcms2 \
    --with-system-libxml \
    --with-system-icu \
    --with-system-expat \
    --with-system-curl \
    --with-system-harfbuzz \
    --with-system-glew \
    --with-system-openssl \
    \
    --with-system-cppunit \
    --with-system-glm \
    --with-system-mdds \
    --with-system-redland \
    --with-system-libabw \
    --with-system-libwps \
    --with-system-libwpg \
    --with-system-libwpd \
    --with-system-libcdr \
    --with-system-libcmis \
    --with-system-libebook \
    --with-system-libmwaw \
    --with-system-libetonyek \
    --with-system-libvisio \
    --with-system-libmspub \
    --with-system-libpagemaker \
    --with-system-libodfgen \
    --with-system-libgltf \
    --with-system-libexttextcat \
    --with-system-clucene \
    --with-system-vigra \
    --with-system-hunspell \
    --with-system-mythes \
    --with-system-altlinuxhyph \
    --with-system-graphite \
"

PACKAGECONFIG ??= " \
    gtk \
    mariadb \
    postgresql \
"

PACKAGECONFIG[gtk] = "--enable-gtk , --disable-gtk, gtk+ cairo"
PACKAGECONFIG[gtk3] = "--enable-gtk3 , --disable-gtk3, gtk+3 cairo"

PACKAGECONFIG[mariadb] = "--enable-ext-mariadb-connector --enable-bundle-mariadb --with-system-mariadb, --disable-ext-mariadb-connector --disable-bundle-mariadb, mariadb"
PACKAGECONFIG[postgresql] = "--enable-postgresql-sdbc --with-system-postgresql, --disable-postgresql-sdbc, postgresql"

do_configure() {
    olddir=`pwd`
    cd ${S}
    aclocal --system-acdir=${B}/aclocal-copy/ -I ${S}/m4
    gnu-configize
    autoconf
    cd $olddir
    export PYTHON_CFLAGS=-I${STAGING_INCDIR}/${PYTHON_DIR}
    export PYTHON_LIBS="-L${STAGING_LIBDIR} -lpython${PYTHON_BASEVERSION}"
    oe_runconf

    mkdir -p ${B}/workdir/Executable

    # icu binaries are expected in our build tree
    mkdir -p ${B}/workdir/UnpackedTarball/icu/source/
    cd ${B}/workdir/UnpackedTarball/icu/source/
    icu_bindir=`find ${STAGING_DATADIR_NATIVE}/icu -name bin`
    ln -sf $icu_bindir

    # link to native saxparser.rdb - cross version of that file is useless
    sed -i 's:%STAGING_LIBDIR_NATIVE%:${STAGING_LIBDIR_NATIVE}:g' ${S}/solenv/gbuild/TargetLocations.mk

    # ensure gengal loads native libraries
    sed -i 's:%STAGING_LIBDIR_NATIVE%:${STAGING_LIBDIR_NATIVE}:g' ${S}/solenv/gbuild/Gallery.mk
}

do_install() {
    make DESTDIR=${D} distro-pack-install
}


FILES_${PN} += " \
    ${datadir}/mime \
    ${datadir}/application-registry \
    ${datadir}/mimelnk \
    ${datadir}/icons \
    ${datadir}/appdata \
    ${datadir}/mime-info \
    ${datadir}/mime/packages \
    \
"

PACKAGE_BEFORE_PN += "${PN}-sdk"
FILES_${PN}-sdk = " \
    ${libdir}/libreoffice/sdk \
"
INSANE_SKIP_${PN}-sdk += "dev-so staticdev"

FILES_${PN}-dbg += " \
    ${libdir}/libreoffice/*/.debug \
    ${libdir}/libreoffice/*/*/.debug \
    ${libdir}/libreoffice/*/*/*/.debug \
"

# based http://pkgs.fedoraproject.org/cgit/rpms/libreoffice.git/tree/libreoffice.spec
LO_LANGUAGE_FILES = " \
    ${libdir}/libreoffice/share/autocorr/*%{1}.dat \
    ${libdir}/libreoffice/program/resource/*%{1}.res \
    ${libdir}/libreoffice/share/config/soffice.cfg/modules/*/ui/res/%{1}.zip \
    ${libdir}/libreoffice/share/config/soffice.cfg/*/ui/res/%{1}.zip \
    ${libdir}/libreoffice/share/template/%{1} \
    ${libdir}/libreoffice/share/registry/Langpack-%{1}.xcd \
    ${libdir}/libreoffice/share/registry/res/registry_%{1}.xcd \
    ${libdir}/libreoffice/share/registry/res/fcfg_langpack_%{1}.xcd \
"

python lo_do_split_locales() {
    import glob, subprocess

    packages = (d.getVar('PACKAGES', True) or "").split()

    datadir = d.getVar('datadir', True)
    if not datadir:
        bb.note("datadir not defined")
        return

    dvar = d.getVar('PKGD', True)
    pn = d.getVar('LOCALEBASEPN', True)

    if pn + '-locale' in packages:
        packages.remove(pn + '-locale')

    # extract locales from translation source path
    # this won't add en-US which comes with base sources
    locales = []
    transpaths = glob.glob('%s/translations/source/*' % d.getVar('S', True))
    for l in transpaths:
        locale = l.replace('%s/translations/source/' % d.getVar('S', True), '')
        if not locale in locales:
            locales.append(locale)

    # find the paths where locales are found
    # en-US is fallback so it should be implemented everywhere (= folder created)
    langfallback = 'en-US'
    findCMD = 'find %s -name "*%s*"' % (dvar, langfallback)
    fallbacksearchresult = subprocess.Popen(findCMD, stdout=subprocess.PIPE, shell=True).communicate()[0]
    # uncomment to see if we need more entries in LO_LANGUAGE_FILES
    # bb.note("fallbacksearchresult = %s" % fallbacksearchresult.replace(dvar, ''))

    # check for files matching at each location / language
    langfiles = dict()
    for transvar in d.getVar('LO_LANGUAGE_FILES', True).split():
        filesfound = 0
        for locale in sorted(locales):
            pattern = transvar.replace('%{1}', locale)
            translocation = '%s%s' % (dvar, pattern)
            transfiles = glob.glob(translocation)
            if transfiles:
                if locale not in langfiles:
                    langfiles[locale] = ''
                langfiles[locale] = '%s %s' % (langfiles[locale], pattern)
                filesfound = 1
        if filesfound:
            # remove from fallbacksearchresult REVISIT
            pattern = transvar.replace('%{1}', langfallback)
            translocation = '%s%s' % (dvar, pattern)
            if translocation in fallbacksearchresult:
                fallbacksearchresult.replace(translocation, '')
        else:
            bb.warn('%s language file pattern not found:  %s' % (d.getVar('PN', True), transvar))

    # setup packages for locales with files found
    summary = d.getVar('SUMMARY', True) or pn
    description = d.getVar('DESCRIPTION', True) or ""
    locale_section = d.getVar('LOCALE_SECTION', True)
    mlprefix = d.getVar('MLPREFIX', True) or ""
    for locale in locales:
        if locale in langfiles:
            ln = legitimize_package_name(locale)
            pkg = pn + '-locale-' + ln
            packages.insert(0, pkg)
            d.setVar('FILES_' + pkg, langfiles[locale] )
            d.setVar('RRECOMMENDS_' + pkg, '%svirtual-locale-%s' % (mlprefix, ln))
            d.setVar('RPROVIDES_' + pkg, '%s-locale %s%s-translation' % (pn, mlprefix, ln))
            d.setVar('SUMMARY_' + pkg, '%s - %s translations' % (summary, l))
            d.setVar('DESCRIPTION_' + pkg, '%s  This package contains language translation files for the %s locale.' % (description, l))
            if locale_section:
                d.setVar('SECTION_' + pkg, locale_section)

    d.setVar('PACKAGES', ' '.join(packages))

    return
}

PACKAGESPLITFUNCS_prepend = "lo_do_split_locales "
