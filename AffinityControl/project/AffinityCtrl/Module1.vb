Module Module1

    Private Const END_LINE = "_RESPONSE_END_"
    Private Const FAILED = "_RESPONSE_FAILED_"

    Sub Main()
        For Each arg As String In Environment.GetCommandLineArgs
            If arg.ToLower.Equals("-pipe") Then
                pipe()
            End If
        Next
        help()
    End Sub

    Sub help()
        Console.WriteLine("Commands:")
        Console.WriteLine("   list_processes")
        Console.WriteLine("   restore_affinity")
        Console.WriteLine("   set_affinity")
        Console.WriteLine("        chrome,150;blender,150 [process_name,2^#ofcores;]")
        Console.WriteLine("   exit")
    End Sub

    Sub pipe()
        While True
            Dim cmd = Console.ReadLine().ToLower()

            Select Case cmd
                Case "list_processes"
                    list_processes()

                Case "set_affinity"
                    set_affinity()

                Case "restore_affinity"
                    restore_affinity()

                Case "exit"
                    Environment.Exit(0)

                Case Else
                    Console.WriteLine(FAILED)
                    Continue While
            End Select
            Console.WriteLine(END_LINE)

        End While
    End Sub

    Sub restore_affinity()
        Dim affinity = (2 ^ Environment.ProcessorCount) - 1

        For Each proc As Process In Process.GetProcesses()
            Try
                proc.ProcessorAffinity = affinity
            Catch ex As Exception

            End Try
        Next

    End Sub

    Sub set_affinity()
        Dim affinity = Console.ReadLine().Split(";")

        For Each proc As Process In Process.GetProcesses()
            For Each item In affinity

                Dim spec = item.Split(",")
                If spec.Length >= 2 And spec.ElementAt(0).Equals(proc.ProcessName) Then
                    Try
                        proc.ProcessorAffinity = CType(spec.ElementAt(1), Integer)
                    Catch ex As Exception

                    End Try
                End If

            Next
        Next

    End Sub

    Sub list_processes()
        Dim long_line = ""
        For Each proc As Process In Process.GetProcesses()
            Try
                long_line += proc.ProcessName.ToString + "," + proc.Id.ToString + "," + proc.BasePriority.ToString + "," + proc.ProcessorAffinity.ToString + ";"
            Catch ex As Exception

            End Try
        Next
        Console.WriteLine(long_line)
    End Sub

End Module
