本例主要演示springboot与jodconverter整合实现office在线预览

> 实现预览的核心步骤
  - 1.上传文档到office服务器（office服务器要自己实现，而且服务器要装有libreoffice）
  - 2.office服务器读取上传的文档，并将上传文档转换成pdf，或者html（通常excel格式建议转换成html）
  - 3.从office服务器上拉取已经转换好的文档，前端页面可以利用iframe或者embed标签来显示pdf文档，型如
    ```
    <iframe id="iframe" frameborder="0" src="/preview/readFile?fileName=${fileName}" style="width:100%;"></iframe>
    或者
    <embed src="/preview/readFile?fileName=${fileName}" width="100%" height="1500" />
    ```
  - 4.启动后访问:http://localhost:8088
> libreoffice安装
  - window安装，可以通过下方链接下载，并按提示一步一步安装
  [window安装](https://zh-cn.libreoffice.org/download/download/)
  
  - centos安装
    
    ```
    #安装文件
    
    yum -y install libreoffice
    
    #安装中文包
    
    yum -y install libreoffice-langpack-zh-Han*
    
    #安装的目录在/usr/lib64/libreoffice
    ```
    
> libreoffice在centos/window上通过命令行进行测试shell指令如下
  ```
  #centos
  /usr/bin/libreoffice  --invisible --convert-to pdf  <待转换的word路径> --outdir <生成的pdf路径>
  
  比如：/usr/bin/libreoffice  --invisible --convert-to pdf  test.txt --outdir abc
  
  
  # windows
  soffice.exe --headless --invisible --convert-to pdf test.txt --outdir d:\abc
  ```

# OpenOffice与LibreOffice

>Apache OpenOffice与LibreOffice都发源于OpenOffice.org这个开源的office办公套件。另外还有一个Oracle Open Office，但它是一个闭源的产品，现在已经消失了。基于OpenOffice.org的这两个开源office软件有持续的更新，并且各自有大量的用户在使用。如果你不知道该选择哪一个，那么请继续阅读，我们将告诉你它们之间的区别。
> https://www.linuxdashen.com/openoffice%E4%B8%8Elibreoffice%EF%BC%8C%E5%93%AA%E4%B8%AA%E6%9B%B4%E9%80%82%E5%90%88%E4%BD%A0
## OpenOffice与LibreOffice共存局面的由来
故事开始于1999年，Sun Microsystems收购了StarOffice办公套件。一年以后，StarOffice被重命名为OpenOffice.org，并且开放源代码。Sun Microsystems公司的员工和其他的志愿者持续不断地改善它。2010年，Oracle公司收购Sun Microsystems，StarOffice被重命名为Oracle Open Office，但它后来停止了开发。Sun Microsystem的一些员工不愿参与Oracle Open Office的开发，所以在OpenOffice.org的基础上开发出了一个衍生版，即LibreOffice。大多数Linux发行版本，包括ubuntu，将LibreOffice作为默认的office办公软件。

2011年，Oracle将OpenOffice.org的商标权和代码捐赠给Apache软件基金会，继续OpenOffice.org的开发工作，并将它重命名为Apache OpenOffice。

##  OpenOffice与LibreOffice之间的区别
Apache OpenOffice和LibreOffice都是免费开源的，都有持续的更新。它们都支持Windows、Linux和OS X，而且它们都提供word文档编辑、电子表格文件编辑、演示文稿编辑和数据库管理，用户界面也十分相似。

它们的Word处理程序使用了不同的主题。与OpenOffice不同的是，LibreOffice默认隐藏了侧边工具栏。如果电脑的屏幕很大，那么侧边工具栏可以提供一些方便。如果要在LibreOffice中启用侧边工具栏，那么选择工具选项LibreOffice高级，然后勾选启用实验性功能。重启word处理程序，选择视图侧边栏

openoffice

另外一个区别是LibreOffice的状态栏位于窗口底部，并且提供了实时更新字数统计的功能。在OpenOffice中查看字数统计，你需要选择工具字数统计

如果你要在文件中嵌入字体，那么你只能使用LibreOffice来实现，OpenOffice没有这个功能。在文件中嵌入字体可以保证文件在任何电脑上都显示一样的字体，即使电脑上没有安装那种字体。这跟谷歌网络字体的效果一样。具体步骤是选择文件属性字体

LibreOffice的演示文稿程序可以让用户在安卓设备上控制幻灯片的放映。LibreOffice的电子表格程序可以创建数据表单（data forms）。LibreOffice可以打开和保存.docx，.xlsx，.pptx文件，而OpenOffice只能打开这些格式的文件，不能保存。

## OpenOffice与LibreOffice：不同的发行许可证
OpenOffice的侧边工具栏是Apache软件基金会添加的新功能，LibreOffice的实验性侧边栏与它十分相似。其实LibreOffice是将OpenOffice中相应的代码复制，然后将它嵌入到自己的软件中。因为Apache OpenOffice采用的是Apache发行许可证，而LibreOffice采用的是LGPLv3/MPL双许可证。这两个许可证是兼容的。

另一方面，LibreOffice的一些独特的功能，如嵌入字体，是OpenOffice所不具有的。这是因为它们的许可证兼容是单向的，LibreOffice可以使用OpenOffice的代码，但OpenOffice不可以使用LibreOffice的代码。这意味着，将来OpenOffice所做的改善可以被LibreOffice吸收，而LibreOffice的新功能新特点不能被OpenOffice吸收。

##  我们的建议
我们的建议是使用LibreOffice，因为LibreOffice是大多数Linux发行版的默认office软件，它的更新比OpenOffice的频繁，发展潜力更大。LibreOffice有众多商业公司参与开发，如Red Hat，Canonical，Collabora等，而OpenOffice只有IBM。


中文网:https://zh-cn.libreoffice.org/
 
  