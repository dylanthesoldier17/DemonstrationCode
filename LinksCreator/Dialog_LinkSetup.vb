Imports System.Windows.Forms

Public Class Dialog_LinkSetup

    Private Sub Cancel_Button_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Cancel_Button.Click
        Me.DialogResult = System.Windows.Forms.DialogResult.Cancel
        Me.Close()
    End Sub

    Private Sub OK_Button_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles OK_Button.Click
        Me.DialogResult = System.Windows.Forms.DialogResult.OK
        Do_Functions()
        Me.Close()
    End Sub

    'Actual CMD functions based on radiobuttons and textbox's contents
    Private Sub Do_Functions()
        If RadioButton_Hard_Link.Checked = True Then
            Link_Creator.ListBox_Links.Items.Add("cmd /c move /y " + Chr(34) + TextBox_Dialog_Destination.Text + "\" + TextBox_FileName.Text + Chr(34) + " %temp%")
            Shell("cmd /c mklink /h " + Chr(34) + TextBox_Dialog_Destination.Text + "\" + TextBox_FileName.Text + Chr(34) + " " + Chr(34) + TextBox_Dialog_Target.Text + Chr(34), AppWinStyle.Hide)
        ElseIf RadioButton_Mount_Folder.Checked = True Then
            Link_Creator.ListBox_Links.Items.Add("cmd /c subst /d " + ComboBox_MountLetter.SelectedItem + ": ")
            Shell("cmd /c subst " + ComboBox_MountLetter.SelectedItem + ": " + Chr(34) + TextBox_Dialog_Target.Text + Chr(34), AppWinStyle.Hide)
        ElseIf RadioButton_Sym_File.Checked = True Then
            Link_Creator.ListBox_Links.Items.Add("cmd /c move /y " + Chr(34) + TextBox_Dialog_Destination.Text + "\" + TextBox_FileName.Text + Chr(34) + " %temp%")
            Shell("cmd /c mklink " + Chr(34) + TextBox_Dialog_Destination.Text + "\" + TextBox_FileName.Text + Chr(34) + " " + Chr(34) + TextBox_Dialog_Target.Text + Chr(34), AppWinStyle.Hide)
        ElseIf RadioButton_Sym_Folder.Checked = True Then
            Link_Creator.ListBox_Links.Items.Add("cmd /c rmdir " + Chr(34) + TextBox_Dialog_Destination.Text + "\" + TextBox_FileName.Text + "\" + Chr(34))
            Shell("cmd /c mklink /d " + Chr(34) + TextBox_Dialog_Destination.Text + "\" + TextBox_FileName.Text + Chr(34) + " " + Chr(34) + TextBox_Dialog_Target.Text + "\" + Chr(34), AppWinStyle.Hide)
        End If
    End Sub

    'Shows the browsing file/folder dialog & functions based on radiobuttons
    '--- start ---
    Private Sub Get_The_File_or_Folder() Handles Button__Dialog_Browse.Click
        If RadioButton_Hard_Link.Checked = True Then 'Hard Link Files
            File_Browse(TextBox_Dialog_Target, "Choose the file to be linked!", True)
            Folder_Browse(TextBox_Dialog_Destination, "Choose the folder to put the linked file too", True)
        ElseIf RadioButton_Sym_File.Checked = True Then 'Symbolic Link Files
            File_Browse(TextBox_Dialog_Target, "Choose the file to be linked!", True)
            Folder_Browse(TextBox_Dialog_Destination, "Choose the destination folder! Then add the file's name and extenstion", True)
        ElseIf RadioButton_Sym_Folder.Checked = True Then 'Symbolic Link Folders
            Folder_Browse(TextBox_Dialog_Target, "Choose the folder to be linked!", True)
            Folder_Browse(TextBox_Dialog_Destination, "Choose the directory to put the linked folder in!", True)
        ElseIf RadioButton_Mount_Folder.Checked = True Then 'Mount Folder
            Folder_Browse(TextBox_Dialog_Target, "Choose the folder to be Mounted!", True)
        End If
    End Sub

    Private Sub File_Browse(ByVal The_Textbox As TextBox, ByVal The_Description As String, ByVal Disable_Textbox As Boolean)
        Dim The_Dialog As New Windows.Forms.OpenFileDialog
        The_Dialog.FileName = The_Description
        If The_Dialog.ShowDialog() = Windows.Forms.DialogResult.OK Then
            The_Textbox.Text = The_Dialog.FileName
        End If
        If Disable_Textbox = True Then
            The_Textbox.Enabled = False
        End If
    End Sub

    Private Sub Folder_Browse(ByVal TheTextbox As TextBox, ByVal TheDescription As String, ByVal Disable_Textbox As Boolean)
        Dim folder As New FolderBrowserDialog
        folder.Description = TheDescription
        If folder.ShowDialog = Windows.Forms.DialogResult.OK Then
            TheTextbox.Text = folder.SelectedPath
        End If
        If Disable_Textbox = True Then
            TheTextbox.Enabled = False
        End If
    End Sub
    '--- end ---

    'Resets textbox's text to nothing & enable it. When any radiobutton is changed.
    'Addition changes when mount folder radiobutton is checked
    'Additional changes for textbox_filename
    Private Sub Reset_Textboxs() Handles RadioButton_Hard_Link.CheckedChanged, RadioButton_Mount_Folder.CheckedChanged, RadioButton_Sym_File.CheckedChanged, RadioButton_Sym_Folder.CheckedChanged
        TextBox_Dialog_Target.Text = ""
        TextBox_Dialog_Target.Enabled = True
        TextBox_Dialog_Destination.Text = ""
        TextBox_Dialog_Destination.Enabled = True

        If RadioButton_Mount_Folder.Checked = True Then 'Mount Folder Checked = true
            ComboBox_MountLetter.Enabled = True
            TextBox_Dialog_Destination.Enabled = False
        ElseIf RadioButton_Mount_Folder.Checked = False Then 'Mount Folder Checked = false
            ComboBox_MountLetter.Enabled = False
            TextBox_Dialog_Destination.Enabled = True
        End If

        'Sets textbox_filename enabled state based on radiobutton active
        If RadioButton_Hard_Link.Checked = True Then 'HardLink Checked
            TextBox_FileName.Enabled = True
        ElseIf RadioButton_Sym_File.Checked = True Then 'Symbolic Link File Checked
            TextBox_FileName.Enabled = True
        ElseIf RadioButton_Sym_Folder.Checked = True Then 'Symbolic Folder Checked
            TextBox_FileName.Enabled = True
        ElseIf RadioButton_Mount_Folder.Checked = True Then
            TextBox_FileName.Enabled = False
        End If
    End Sub

End Class

