let proLanguageSelect = document.getElementById('prolang');
let themeSelect = document.getElementById('theme');
let editor = ace.edit("editor");
let edit = document.getElementById('editor');
let editMenu = document.getElementById('editor-menu');
let runBtn = document.getElementById('run');
let screenBtn = document.getElementById('screen');
let screenImg = document.getElementById('screen-img');
let codeDiv = document.getElementById('code-edit');
let hiddenCode = document.getElementById('hiddencode');
let form = document.getElementById('editorForm');

editor.setOptions({
    autoScrollEditorIntoView: false,
    copyWithEmptySelection: true,
});

//default values for editor
editor.setTheme("../ace/theme/"+themeSelect.value);
editor.session.setMode("ace/mode/"+proLanguageSelect.value);
editor.setValue(`public class MainClass {
    public static void main(String[] Args) {
        System.out.println("Hello World");
    }
}`);

// language changer for editor
proLanguageSelect.addEventListener('change', () => {
    if(proLanguageSelect.value=='c'||proLanguageSelect.value=='cpp') {
        editor.session.setMode("ace/mode/c_cpp");
    } else {
        editor.session.setMode("ace/mode/"+proLanguageSelect.value);
    }
    
    
    if(proLanguageSelect.value=='javascript')
        editor.setValue(`console.log('Hello World');`);
    
    if(proLanguageSelect.value=='java')
        editor.setValue(`public class MainClass {
    public static void main(String[] Args) {
        System.out.println("Hello World");
    }
}`);

    if(proLanguageSelect.value=='python')
        editor.setValue(`print 'Hello World'`);

    if(proLanguageSelect.value=='c')
    editor.setValue(`#include <stdio.h>\n
int main() {
    printf("%s","Hello World");
    return 0;
}`);
    
    if(proLanguageSelect.value=='php')
        editor.setValue(`<?php
echo "My first PHP script!";
?>`);
    
    if(proLanguageSelect.value=='cpp')
    editor.setValue(`#include <iostream>\n
using namespace std;
int main() {
    cout<<"Hello World";
    return 0;
}`);
        
});

// theme changer for editor
themeSelect.addEventListener('change', () => {
    editor.setTheme("../ace/theme/"+themeSelect.value);
    if(themeSelect.value=='xcode') {
        proLanguageSelect.style.backgroundColor = 'white';
        proLanguageSelect.style.color = 'black';
        proLanguageSelect.style.border = '1px solid rgb(141,141,141';
        themeSelect.style.backgroundColor = 'white';
        themeSelect.style.color = 'black';
        themeSelect.style.border = '1px solid rgb(141,141,141';
        runBtn.style.backgroundColor = 'rgb(230, 227, 227)';
        editMenu.style.backgroundColor = 'rgb(230, 227, 227)';
        editMenu.style.border = '1px solid #DDD';
        editMenu.style.borderRadius = '10px 10px 0px 0px';
        editMenu.style.borderBottom = 'none';
        } else {
        proLanguageSelect.style.backgroundColor = 'black';
        proLanguageSelect.style.color = 'white';
        themeSelect.style.backgroundColor = 'black';
        themeSelect.style.color = 'white';
        editMenu.style.backgroundColor = 'rgb(40, 45, 56)';
        runBtn.style.backgroundColor = 'rgb(40, 45, 56)';
    }
});

form.addEventListener("submit", () => {
    var code1 = editor.getValue();
    hiddenCode.value = code1;
});