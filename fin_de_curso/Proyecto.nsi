# Instalador que nos pregunta donde queremos instalar
# Ruta por defecto: archivos de programa
# Incluye informacion sobre la desisntalacion

# Nombre del instalador
Name "Proyecto"

# The file to write
OutFile "Proyecto.exe"

# The default installation directory
InstallDir $PROGRAMFILES\Proyecto

# Pedimos permisos para Windows 7
RequestExecutionLevel admin

# Pantallas que hay que mostrar del instalador

Page directory
Page instfiles

#Seccion principal
Section

  # Establecemos el directorio de salida al directorio de instalacion
  SetOutPath $INSTDIR
  
  # Creamos el desinstalador
  writeUninstaller "$INSTDIR\uninstall.exe"
  
  # Ponemos los archivos necesarios
  File /r "D:\Proyectos\NetBeans\Proyecto\Proyecto_develop\dist\*"
  
  #Añadimos información para que salga en el menú de desinstalar de Windows
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Proyecto" \
                 "DisplayName" "Proyecto"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Proyecto" \
                 "Publisher" "Plamen Georgiev Petkov"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Proyecto" \
                 "UninstallString" "$\"$INSTDIR\uninstall.exe$\""
  
# Fin de la seccion
SectionEnd

# seccion del desintalador
section "uninstall"
 
    # borramos el desintalador primero
    delete "$INSTDIR\uninstall.exe"
 
    # borramos el acceso directo del menu de inicio
    delete  "$INSTDIR\"
	
	RmDir /r /REBOOTOK "$INSTDIR*"
	
	#Borramos la entrada del registro
	DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Proyecto"
 
# fin de la seccion del desinstalador
sectionEnd