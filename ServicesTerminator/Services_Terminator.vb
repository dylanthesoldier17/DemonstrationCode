Imports System.IO
Imports System.ServiceProcess
Imports Microsoft.VisualBasic.FileIO.FileSystem
Public Class Services_Terminator
    Dim BoostOn As Boolean = False
    Dim OnlineMode As Boolean = True
    Dim Required_Offline_Services_List, Required_Online_Services_List, _
        Custom_Offline_Services_List, Custom_Online_Services_List, Running_Services_List As New ArrayList
    Dim CurrentDir As String = Environment.CurrentDirectory + "\"
    Dim arguments = My.Application.CommandLineArgs

#Region "Form Events"

    'Loading
    Private Sub Form_Load() Handles MyBase.Load
        File_Check()
        Set_OnlineMode_From_Radiobuttons()
        Process_Arguments()
        Me.MaximizeBox = False
    End Sub

    Private Sub File_Check()
        If FileExists(CurrentDir + "Running_Services.list") = True Then
            BoostOn = True
            Dim input As New StreamReader(CurrentDir + "Running_Services.list")
            Do Until input.EndOfStream = True
                Running_Services_List.Add(input.ReadLine)
            Loop
            input.Close()
            Restart_Services()
        ElseIf FileExists(CurrentDir + "Required_Online_Services.list") = False Then
            MessageBox.Show("I'm missing: Required_Online_Services.list")
            End
        ElseIf FileExists(CurrentDir + "Required_Offline_Services.list") = False Then
            MessageBox.Show("I'm missing: Required_Offline_Services.list")
            End
        ElseIf FileExists(CurrentDir + "Custom_Online_Services.list") = False Then
            Process.Start("", CurrentDir + "Custom_Online_Services.list")
        ElseIf FileExists(CurrentDir + "Custom_Offline_Services.list") = False Then
            Process.Start("notepad", CurrentDir + "Custom_Offline_Services.list")
        ElseIf FileExists(CurrentDir + "Online.txt") = True Then
            RadioButton_OnlineMode.Checked = True
            DeleteFile(CurrentDir + "Online.txt")
        ElseIf FileExists(CurrentDir + "Offline.txt") = True Then
            RadioButton_OfflineMode.Checked = True
            DeleteFile(CurrentDir + "Offline.txt")
        End If
    End Sub

    Private Sub Process_Arguments()
        For Each item As Object In arguments
            Select Case item
                Case "-online_stop"
                    RadioButton_OnlineMode.Checked = True
                    Stop_Services()
                Case "-offline_stop"
                    RadioButton_OfflineMode.Checked = True
                    Stop_Services()
            End Select
        Next
    End Sub

    'Closing
    Private Sub Form_Closing() Handles Me.FormClosing
        If BoostOn = True Then
            Restart_Services()
        End If
        Save_Online_Mode_State()
    End Sub

    Private Sub Save_Online_Mode_State()
        If OnlineMode = True Then
            Dim output As New StreamWriter(CurrentDir + "Online.txt")
            output.Close()
        ElseIf OnlineMode = False Then
            Dim output As New StreamWriter(CurrentDir + "Offline.txt")
            output.Close()
        End If
    End Sub

#End Region

#Region "Stop Functions"
    Private Sub Stop_Services() Handles Button_Stop.Click
        If BoostOn = False Then
            Stop_Step_1()
            Stop_Step_2()
            Stop_Step_3()
            Stop_Step_4()
            BoostOn = True
        Else
            MessageBox.Show("I've already stopped the services!")
        End If
    End Sub

    Private Sub Stop_Step_1() 'Loads services from txt files to appropriate arraylist
        File_To_Collection(CurrentDir + "Required_Online_Services.list", Required_Online_Services_List)
        File_To_Collection(CurrentDir + "Required_Offline_Services.list", Required_Offline_Services_List)
        File_To_Collection(CurrentDir + "Custom_Offline_Services.list", Custom_Offline_Services_List)
        File_To_Collection(CurrentDir + "Custom_Online_Services.list", Custom_Online_Services_List)
    End Sub

    Private Sub Stop_Step_2() 'Loads Running Services to Arraylist
        For Each svc As ServiceController In ServiceController.GetServices
            If svc.Status = ServiceControllerStatus.Running Then
                Running_Services_List.Add(svc.ServiceName.ToString)
            End If
        Next
    End Sub

    Private Sub Stop_Step_3() 'Checks for a match in the Required & custom Services List based on onlinemode boolean
        If OnlineMode = True Then
            Remove_From_ArrayList(Running_Services_List, Required_Online_Services_List)
            Remove_From_ArrayList(Running_Services_List, Custom_Online_Services_List)
        ElseIf OnlineMode = False Then
            Remove_From_ArrayList(Running_Services_List, Required_Offline_Services_List)
            Remove_From_ArrayList(Running_Services_List, Custom_Offline_Services_List)
        End If
    End Sub

    Private Sub Stop_Step_4() 'Stop Services and Saves List
        Dim output As New StreamWriter(CurrentDir + "Running_Services.list")
        For Each item As Object In Running_Services_List
            Change_Service_Status(item, False)
            output.WriteLine(item)
        Next
        output.Close()
    End Sub
#End Region

#Region "Restart Services Function"
    Private Sub Restart_Services() Handles Button_Restart.Click
        If BoostOn = True Then
            For Each item As Object In Running_Services_List
                Change_Service_Status(item, True)
            Next
            BoostOn = False
            DeleteFile(CurrentDir + "Running_Services.list")
        Else
            MessageBox.Show("The services are already restarted!")
        End If
    End Sub
#End Region

#Region "NotifiyIcon Context Menu Clicks"

    Private Sub ShowToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ShowToolStripMenuItem.Click
        WindowState = FormWindowState.Normal
    End Sub

    Private Sub ChangeServicesToolStripMenuItem1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ChangeServicesToolStripMenuItem1.Click
        Change_Service_List()
    End Sub

    Private Sub AboutMeToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles AboutMeToolStripMenuItem.Click
        Dialog_AboutMe.ShowDialog()
    End Sub

    Private Sub CloseToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles CloseToolStripMenuItem.Click
        Me.Close()
    End Sub

    Private Sub StopToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles StopToolStripMenuItem.Click
        Stop_Services()
    End Sub

    Private Sub RestartToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RestartToolStripMenuItem.Click
        Restart_Services()
    End Sub

#End Region

    Private Sub Set_OnlineMode_From_Radiobuttons() Handles RadioButton_OfflineMode.CheckedChanged, RadioButton_OnlineMode.CheckedChanged
        If RadioButton_OfflineMode.Checked = True Then
            OnlineMode = False
        ElseIf RadioButton_OnlineMode.Checked = True Then
            OnlineMode = True
        End If
    End Sub

    Private Sub Change_Service_List() Handles ChangeServicesToolStripMenuItem_Change.Click
        If BoostOn = True Then
            Restart_Services()
        End If
        If OnlineMode = True Then
            Dim proc = Process.Start("notepad", CurrentDir + "Custom_Online_Services.list")
            proc.WaitForExit()
            Custom_Online_Services_List.Clear()
            File_To_Collection(CurrentDir + "Custom_Online_Services.list", Custom_Online_Services_List)
        ElseIf OnlineMode = False Then
            Dim proc = Process.Start("notepad", CurrentDir + "Custom_Offline_Services.list")
            proc.WaitForExit()
            Custom_Offline_Services_List.Clear()
            File_To_Collection(CurrentDir + "Custom_Offline_Services.list", Custom_Offline_Services_List)
        End If
    End Sub

End Class
