Imports System.Windows.Forms

Public Class Dialog_AboutMe

    Private Sub OK_Button_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles OK_Button.Click
        Me.Close()
    End Sub

    Private Sub PictureBox_Logo_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles PictureBox_Logo.Click
        Process.Start("http://www.dylanthesoldier.com")
    End Sub
End Class
