<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Link_Creator
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container()
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(Link_Creator))
        Me.ContextMenuStrip_Main = New System.Windows.Forms.ContextMenuStrip(Me.components)
        Me.MakeToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.ToolStripSeparator1 = New System.Windows.Forms.ToolStripSeparator()
        Me.RemoveToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.RemoveFromListToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.ToolStripSeparator2 = New System.Windows.Forms.ToolStripSeparator()
        Me.CloseToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.ListBox_Links = New System.Windows.Forms.ListBox()
        Me.ContextMenuStrip_Main.SuspendLayout()
        Me.SuspendLayout()
        '
        'ContextMenuStrip_Main
        '
        Me.ContextMenuStrip_Main.Items.AddRange(New System.Windows.Forms.ToolStripItem() {Me.MakeToolStripMenuItem, Me.ToolStripSeparator1, Me.RemoveToolStripMenuItem, Me.RemoveFromListToolStripMenuItem, Me.ToolStripSeparator2, Me.CloseToolStripMenuItem})
        Me.ContextMenuStrip_Main.Name = "ContextMenuStrip_Main"
        Me.ContextMenuStrip_Main.Size = New System.Drawing.Size(186, 126)
        '
        'MakeToolStripMenuItem
        '
        Me.MakeToolStripMenuItem.Name = "MakeToolStripMenuItem"
        Me.MakeToolStripMenuItem.Size = New System.Drawing.Size(185, 22)
        Me.MakeToolStripMenuItem.Text = "Make Links"
        '
        'ToolStripSeparator1
        '
        Me.ToolStripSeparator1.Name = "ToolStripSeparator1"
        Me.ToolStripSeparator1.Size = New System.Drawing.Size(182, 6)
        '
        'RemoveToolStripMenuItem
        '
        Me.RemoveToolStripMenuItem.Name = "RemoveToolStripMenuItem"
        Me.RemoveToolStripMenuItem.Size = New System.Drawing.Size(185, 22)
        Me.RemoveToolStripMenuItem.Text = "Remove Selected File"
        '
        'RemoveFromListToolStripMenuItem
        '
        Me.RemoveFromListToolStripMenuItem.Name = "RemoveFromListToolStripMenuItem"
        Me.RemoveFromListToolStripMenuItem.Size = New System.Drawing.Size(185, 22)
        Me.RemoveFromListToolStripMenuItem.Text = "Remove From List"
        '
        'ToolStripSeparator2
        '
        Me.ToolStripSeparator2.Name = "ToolStripSeparator2"
        Me.ToolStripSeparator2.Size = New System.Drawing.Size(182, 6)
        '
        'CloseToolStripMenuItem
        '
        Me.CloseToolStripMenuItem.Name = "CloseToolStripMenuItem"
        Me.CloseToolStripMenuItem.Size = New System.Drawing.Size(185, 22)
        Me.CloseToolStripMenuItem.Text = "Close"
        '
        'ListBox_Links
        '
        Me.ListBox_Links.BackColor = System.Drawing.Color.Black
        Me.ListBox_Links.ContextMenuStrip = Me.ContextMenuStrip_Main
        Me.ListBox_Links.Dock = System.Windows.Forms.DockStyle.Fill
        Me.ListBox_Links.ForeColor = System.Drawing.SystemColors.Window
        Me.ListBox_Links.FormattingEnabled = True
        Me.ListBox_Links.Location = New System.Drawing.Point(0, 0)
        Me.ListBox_Links.Name = "ListBox_Links"
        Me.ListBox_Links.Size = New System.Drawing.Size(533, 386)
        Me.ListBox_Links.TabIndex = 1
        '
        'Link_Creator
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(533, 386)
        Me.Controls.Add(Me.ListBox_Links)
        Me.Icon = CType(resources.GetObject("$this.Icon"), System.Drawing.Icon)
        Me.Name = "Link_Creator"
        Me.Text = "Link Creator"
        Me.ContextMenuStrip_Main.ResumeLayout(False)
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents ContextMenuStrip_Main As System.Windows.Forms.ContextMenuStrip
    Friend WithEvents MakeToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents RemoveToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents ToolStripSeparator2 As System.Windows.Forms.ToolStripSeparator
    Friend WithEvents CloseToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents ListBox_Links As System.Windows.Forms.ListBox
    Friend WithEvents RemoveFromListToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents ToolStripSeparator1 As System.Windows.Forms.ToolStripSeparator

End Class
