Imports System.IO
Imports Microsoft.VisualBasic.FileIO.FileSystem
Public Class Link_Creator
    Dim My_Path As String = Environment.CurrentDirectory + "\"

#Region "Context Menu Clicks"
    'Executes a shell of the command in the listbox
    Private Sub RemoveToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RemoveToolStripMenuItem.Click
        If MessageBox.Show("Are You SURE? You want to delete this file!", "", MessageBoxButtons.YesNo) = Windows.Forms.DialogResult.Yes Then
            Shell(ListBox_Links.SelectedItem, AppWinStyle.Hide)
            ListBox_Links.Items.Remove(ListBox_Links.SelectedItem)
        End If
    End Sub

    Private Sub RemoveFromListToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RemoveFromListToolStripMenuItem.Click
        If MessageBox.Show("Are You SURE? You want to remove the selected it from the list!", "", MessageBoxButtons.YesNo) = Windows.Forms.DialogResult.Yes Then
            ListBox_Links.Items.Remove(ListBox_Links.SelectedItem)
        End If
    End Sub

    Private Sub CloseToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles CloseToolStripMenuItem.Click
        Me.Close()
    End Sub

    Private Sub MakeToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MakeToolStripMenuItem.Click
        Dialog_LinkSetup.ShowDialog()
    End Sub
#End Region

    'Does a file check and it it exists. It loads the listbox from the file
    Private Sub On_Load() Handles MyBase.Load
        If FileExists(Environment.CurrentDirectory + "\Links.list") = False Then
            Dim str = File.Create(Environment.CurrentDirectory + "\Links.list")
            str.Close()
        Else

        End If
        Dim input As New StreamReader(Environment.CurrentDirectory + "\Links.list")
        Do Until Input.EndOfStream = True
            ListBox_Links.Items.Add(Input.ReadLine)
        Loop
        Input.Close()
    End Sub

    'Actual Save Function
    Private Sub Save_Listbox() Handles MyBase.FormClosing
        Dim output As New StreamWriter(Environment.CurrentDirectory + "\Links.list")
        For Each item As Object In ListBox_Links.Items
            output.WriteLine(item)
        Next
        output.Close()
    End Sub


End Class