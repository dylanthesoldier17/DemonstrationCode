Imports System.IO

Public Class WindowsUpdateInstaller_Form
    Dim FolderDialog As New FolderBrowserDialog
    Dim GotFiles As Boolean = False
    Dim working As Boolean = False

    'List the files in the folder that are .msu in the listbox
    Private Sub Get_TheFiles() Handles Button_Browse.Click
        If FolderDialog.ShowDialog = Windows.Forms.DialogResult.OK Then
            Dim TheFolder As New IO.DirectoryInfo(FolderDialog.SelectedPath)
            For Each TheFile In TheFolder.GetFiles
                If TheFile.Extension = ".msu" Then
                    ListBox_TheFiles.Items.Add(TheFile.FullName)
                End If
            Next
            ProgressBar_Main.Maximum = ListBox_TheFiles.Items.Count
            GotFiles = True
        End If
    End Sub


    Private Sub Do_IT() Handles Button_DoIT.Click
        If GotFiles = True Then
            If working = False Then
                MessageBox.Show("I will become unresponsive for a while!")
                For Each item In ListBox_TheFiles.Items
                    Shell("cmd /c wusa /quiet /norestart " + Chr(34) + item + Chr(34), AppWinStyle.Hide, True)
                    ProgressBar_Main.PerformStep()
                Next
                working = False
                MessageBox.Show("Finished!")
                ListBox_TheFiles.Items.Clear()
            Else
                MessageBox.Show("I'm working on it!")
            End If
        End If
    End Sub

End Class
