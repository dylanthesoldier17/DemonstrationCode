Imports System.IO
Module Module_Services_Terminator_Functions

    Public Sub File_To_Collection(ByVal The_File As String, ByRef The_Collection As ArrayList)
        Dim input As New StreamReader(The_File)
        Do Until input.EndOfStream = True
            The_Collection.Add(input.ReadLine)
        Loop
        input.Close()
    End Sub

    Public Sub Change_Service_Status(ByVal ServiceName As String, ByVal Turn_ON As Boolean)
        If Turn_ON = True Then
            Shell("cmd /c sc start " + ServiceName, AppWinStyle.Hide)
        ElseIf Turn_ON = False Then
            Shell("cmd /c sc stop " + ServiceName, AppWinStyle.Hide)
        End If
    End Sub

    Public Sub Running_Services_To_Collection(ByVal The_Collection As ArrayList)
        For Each svc As ServiceProcess.ServiceController In ServiceProcess.ServiceController.GetDevices
            If svc.Status = ServiceProcess.ServiceControllerStatus.Running Then
                The_Collection.Add(svc.ServiceName.ToString)
            End If
        Next
    End Sub

    Public Sub Remove_From_ArrayList(ByRef theList As ArrayList, ByVal TheArray As ArrayList)
        For Each item As Object In TheArray
            If theList.Contains(item) Then
                theList.Remove(item)
            End If
        Next
    End Sub

End Module
