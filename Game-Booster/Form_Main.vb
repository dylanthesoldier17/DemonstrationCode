Imports Microsoft.VisualBasic.FileIO.FileSystem
Public Class Form_Main
    Dim Boost As Boolean = False
    Dim OnlineMode As Boolean = True
    Dim AppsPath = Environment.CurrentDirectory + "\Apps\"
    Dim proc
    Dim svc

#Region "Form Load"

    Private Sub Form_Main_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        Me.TopMost = True
        Directory_Check()
        Boost_Picture_Toggle()
        Online_Status_Picture_Toogle()
    End Sub

    Private Sub Directory_Check()
        If DirectoryExists(AppsPath) = False Then
            CreateDirectory(AppsPath)
        End If
    End Sub

#End Region

#Region "Toggles  Functions"

    Private Sub Online_Status_Toggle() Handles PictureBox_Online_OFF.Click, PictureBox_Online_ON.Click
        If OnlineMode = True Then
            OnlineMode = False
        ElseIf OnlineMode = False Then
            OnlineMode = True
        End If
        Online_Status_Picture_Toogle()
    End Sub

    Private Sub Online_Status_Picture_Toogle()
        If OnlineMode = True Then
            PictureBox_Online_ON.Visible = True
            PictureBox_Online_OFF.Visible = False
        ElseIf OnlineMode = False Then
            PictureBox_Online_OFF.Visible = True
            PictureBox_Online_ON.Visible = False
        End If
    End Sub

    Private Sub Boost_Picture_Toggle() Handles PictureBox_BoostOFF.Click, PictureBox_BoostON.Click
        If Boost = True Then
            PictureBox_BoostOFF.Visible = False
            PictureBox_BoostON.Visible = True
        ElseIf Boost = False Then
            PictureBox_BoostON.Visible = False
            PictureBox_BoostOFF.Visible = True
        End If
    End Sub

#End Region

    Private Sub PictureBox_Logo_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles PictureBox_Logo.Click
        Process.Start("http://www.dylanthesoldier.com")
    End Sub

#Region "Boost Functions"

    Private Sub Boost_Toogle() Handles PictureBox_BoostON.Click, PictureBox_BoostOFF.Click
        If Boost = True Then
            Boost_Recovery()
        ElseIf Boost = False Then
            Initiate_Boost()
        End If
    End Sub

    Private Sub Initiate_Boost() Handles BoostOnToolStripMenuItem.Click
        Shell("cmd /c taskkill /f /im explorer.exe")
        proc = Process.Start(AppsPath + "Process Controller.exe", "BoostOn")
        If OnlineMode = True Then
            svc = Process.Start(AppsPath + "Services Terminator.exe", "stop_online")
        ElseIf OnlineMode = False Then
            svc = Process.Start(AppsPath + "Services Terminator.exe", "stop_offline")
        End If
        Boost = True
        Boost_Picture_Toggle()
    End Sub

    Private Sub Boost_Recovery() Handles BoostOfToolStripMenuItem.Click
        Shell("cmd /c start %windir%\explorer.exe")
        proc.CloseMainWindow()
        svc.CloseMainWindow()
        Boost = False
        Boost_Picture_Toggle()
    End Sub

#End Region

    Private Sub AboutMeToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles AboutMeToolStripMenuItem.Click
        Dialog_AboutMe.ShowDialog()
    End Sub

    Private Sub CloseToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles CloseToolStripMenuItem.Click
        Me.Close()
    End Sub

    Private Sub Form_Main_FormClosing(ByVal sender As System.Object, ByVal e As System.Windows.Forms.FormClosingEventArgs) Handles MyBase.FormClosing
        If Boost = True Then
            Boost_Recovery()
        End If
    End Sub
End Class
