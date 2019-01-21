Imports System.IO
Public Class Form_Process_Controller
    Dim boostOn As Boolean = False
    Dim pathFile As String = Environment.CurrentDirectory + "\Files\Path.txt"
    Dim arguments As System.Collections.ObjectModel.ReadOnlyCollection(Of String) = My.Application.CommandLineArgs
    Dim affinity As Integer

#Region "Boost ON Functions"

    Private Sub Initiate_Boost() Handles BoostOnToolStripMenuItem.Click
        If boostOn = False Then
            For Each proc As Process In Process.GetProcesses
                Set_Priority_Boost(proc)
                Set_Affinity_Boost(proc)
                Check_Process(proc)
            Next
            boostOn = True
            Timer_Main.Enabled = True
        Else
            MessageBox.Show("I've already boosted your processes!")
        End If
    End Sub

    Private Sub Set_Priority_Boost(ByRef proc As Process)
        Try
            If proc.PriorityClass = ProcessPriorityClass.High Then
                proc.PriorityClass = ProcessPriorityClass.AboveNormal
            ElseIf proc.PriorityClass = ProcessPriorityClass.AboveNormal Then
                proc.PriorityClass = ProcessPriorityClass.BelowNormal
            ElseIf proc.PriorityClass = ProcessPriorityClass.Normal Then
                proc.PriorityClass = ProcessPriorityClass.Idle
            End If
        Catch ex As Exception
        End Try
    End Sub

    Private Sub Set_Affinity_Boost(ByRef proc As Process)
        Try
            proc.ProcessorAffinity = 1
        Catch ex As Exception

        End Try
    End Sub

    Private Sub Check_Process(ByRef Proc As Process)
        Select Case Proc.ProcessName.ToString
            Case "services"
                Check_Process_Step2(Proc)
            Case "svchost"
                Check_Process_Step2(Proc)
            Case "rundll32"
                Check_Process_Step2(Proc)
            Case "taskmgr"
                Proc.PriorityClass = ProcessPriorityClass.High
        End Select
    End Sub

    Private Sub Check_Process_Step2(ByRef proc As Process)
        Try
            proc.PriorityClass = ProcessPriorityClass.Normal
        Catch ex As Exception

        End Try

    End Sub
#End Region

#Region "Boost OFF Functions"

    Private Sub Initiate_Restore() Handles BoostOfToolStripMenuItem.Click
        If boostOn = True Then
            Timer_Main.Enabled = False
            For Each proc As Process In Process.GetProcesses
                Set_Priority_Restore(proc)
                Set_Affinity_Restore(proc)
                Restore_Boosted_Apps(proc)
            Next
            boostOn = False
        Else
            MessageBox.Show("I've already restored your system!")
        End If
    End Sub

    Private Sub Set_Priority_Restore(ByRef proc As Process)
        Try
            If proc.PriorityClass = ProcessPriorityClass.Idle Then
                proc.PriorityClass = ProcessPriorityClass.Normal
            ElseIf proc.PriorityClass = ProcessPriorityClass.BelowNormal Then
                proc.PriorityClass = ProcessPriorityClass.AboveNormal
            ElseIf proc.PriorityClass = ProcessPriorityClass.AboveNormal Then
                proc.PriorityClass = ProcessPriorityClass.High
            End If
        Catch ex As Exception

        End Try
    End Sub

    Private Sub Set_Affinity_Restore(ByRef proc As Process)
        Try
            proc.ProcessorAffinity = affinity
        Catch ex As Exception

        End Try
    End Sub

    Private Sub Restore_Boosted_Apps(ByRef proc As Process)
        For Each item In ListBox_Boosted_Apps.Items
            If proc.ProcessName.ToString = item Then
                proc.PriorityClass = ProcessPriorityClass.Normal
            End If
        Next
    End Sub
#End Region

#Region "Button Clicks"

    Private Sub Button_Boost_ON_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button_Boost_ON.Click
        Initiate_Boost()
    End Sub

    Private Sub Button_Boost_OFF_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button_Boost_OFF.Click
        Initiate_Restore()
    End Sub

#End Region

#Region "Form Load"

    Private Sub Form_Process_Controller_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        Load_Saves_Boosted_Processes()
        Load_Affinty()
        Process_Arguments()
    End Sub

    Private Sub Load_Saves_Boosted_Processes()
        Try
            Load_Apps(pathFile)
        Catch ex As Exception
            If My.Computer.FileSystem.DirectoryExists(Environment.CurrentDirectory + "\Files") = False Then
                My.Computer.FileSystem.CreateDirectory(Environment.CurrentDirectory + "\Files")
            End If
            MessageBox.Show("Missing: " + pathFile + " Which contains the list of boosted apps")
        End Try
    End Sub

    Private Sub Process_Arguments()
        For index As Integer = 0 To arguments.Count - 1
            Select Case arguments.Item(index)
                Case "BoostOn"
                    Initiate_Boost()
                Case "BoostOff"
                    Initiate_Restore()
                Case "Hide"
                    Me.Visible = False
            End Select
        Next
    End Sub

    Private Sub Load_Affinty()
        Dim proc As Process = Process.GetCurrentProcess
        affinity = proc.ProcessorAffinity
    End Sub
#End Region

#Region "Form Close"

    Private Sub Form_Process_Controller_FormClosing(ByVal sender As System.Object, ByVal e As System.Windows.Forms.FormClosingEventArgs) Handles MyBase.FormClosing
        Try
            Save_Apps_To_Boost(pathFile)
        Catch ex As Exception
            MessageBox.Show("Missing: " + pathFile)
        End Try
        If boostOn = True Then
            Initiate_Restore()
        End If
    End Sub

#End Region

#Region "Context Menus"

#Region "Listbox Context Menu"

    Private Sub AddToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles AddToolStripMenuItem.Click
        OpenFileDialog_Main.ShowDialog()
    End Sub

    Private Sub RemoveToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RemoveToolStripMenuItem.Click
        ListBox_Boosted_Apps.Items.Remove(ListBox_Boosted_Apps.SelectedItem)
    End Sub

    Private Sub ClearToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ClearToolStripMenuItem.Click
        If MessageBox.Show("Are You Sure!", "", MessageBoxButtons.YesNo, MessageBoxIcon.Warning) = Windows.Forms.DialogResult.Yes Then
            ListBox_Boosted_Apps.Items.Clear()
        End If
    End Sub


#End Region

#Region "NotifyIcon Context Menu"

    Private Sub CloseToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles CloseToolStripMenuItem.Click
        Me.Close()
    End Sub

    Private Sub AboutMeToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles AboutMeToolStripMenuItem.Click
        Dialog_AboutMe.ShowDialog()
    End Sub

#End Region

#End Region

#Region "Save and Loading Boosted App Functions"

    Private Sub Add_App() Handles OpenFileDialog_Main.FileOk
        Dim proc As Process = Process.Start(OpenFileDialog_Main.FileName)
        Try
            ListBox_Boosted_Apps.Items.Add(proc.ProcessName.ToString)
            proc.Kill()
        Catch ex As Exception
            MessageBox.Show("I can't access that process!")
        End Try
    End Sub

    Private Sub Load_Apps(ByVal file As String)
        Dim input As New StreamReader(file)
        Do Until input.EndOfStream = True
            ListBox_Boosted_Apps.Items.Add(input.ReadLine)
        Loop
        input.Close()
    End Sub

    Private Sub Save_Apps_To_Boost(ByVal file As String)
        Dim output As New StreamWriter(file, False)
        For Each item As String In ListBox_Boosted_Apps.Items
            output.WriteLine(item)
        Next
        output.Close()
    End Sub

#End Region

#Region "Timer Tick Function"

    Private Sub Check_For_Boosted_Apps() Handles Timer_Main.Tick
        For Each proc As Process In Process.GetProcesses
            For Each item As Object In ListBox_Boosted_Apps.Items
                If proc.ProcessName = item Then
                    Try
                        proc.PriorityClass = ProcessPriorityClass.AboveNormal
                        proc.ProcessorAffinity = affinity
                    Catch ex As Exception

                    End Try
                End If
            Next
        Next
    End Sub

#End Region

End Class
