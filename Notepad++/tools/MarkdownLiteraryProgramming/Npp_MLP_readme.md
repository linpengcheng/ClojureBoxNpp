## Usage

You can put pythonscript on toolbar.
You can choose from the config area of Pythonscript: 

- viewer
- out_path: Out directory
- mlt_base_path: resource directory 
  (js,image,tools,css,mlt_common_head.md, etc)
- is auto-clean out directory or file?
- tools path: CuteMarked, MultiMarkdown, Chrome.

### subdirectorys and file of mlt_base_dir

1. out (Changeable)
2. js
3. image
4. css
5. MultiMarkdown (Optional)
6. CuteMarked (Optional)
7. ChromePortable (Optional)
8. MLT_common_head.md

out_dir can be separated from mlt_base_dir(public resource directory), 
but must be on the same disk, in "mlt_common_head.md" file, 
the path starts with the root path, for example:  
"/path_to/mlt_base_dir/js/prismjs/prism.js"

### Viewer
#### CuteMarked (viewer = 1, default)
- Cutemarked supports sidebar outlines, 
  which is helpful for reading code.
- Cutemarked is the only one markdown viewer (editor):
  - native
  - It supports all features of the browser, support:
    - js
	  - image
	  - css
	  - syntax highlighting
    - Mermaid charts
    - Math
- Cutemarked exported HTML has problems 
  displaying syntax highlighting and Mermaid charts.	

#### MultiMarkdown + Chrome (viewer = 2)
- MultiMarkdown support TOC.
- MultiMarkdown does not support the GitHub style 
  code block block and Mermaid charts, 
  I made a correction in py.
- Multimarkdown faster and lighter than pandoc.
- When converted to HTML, it is automatically open with Chrome, 
  and the display works well.

#### MarkdownViewer++ plugin + Chrome (viewer = 3)
- MarkdownViewer++ viewer is fast, only support displaying markdown text. 
- MarkdownViewer++ viewer don't support syntax highlighting, image, js.
- MarkdownViewer++ viewer only displaying Mermaid text.
- MarkdownViewer++ export as html, chrome displaying is ok :
  - syntax highlighting
  - Mermaid charts 
  - image 
  - js

