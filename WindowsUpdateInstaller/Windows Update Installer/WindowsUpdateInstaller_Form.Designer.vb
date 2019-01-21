<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class WindowsUpdateInstaller_Form
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
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(WindowsUpdateInstaller_Form))
        Me.ListBox_TheFiles = New System.Windows.Forms.ListBox()
        Me.Button_Browse = New System.Windows.Forms.Button()
        Me.Button_DoIT = New System.Windows.Forms.Button()
        Me.ProgressBar_Main = New System.Windows.Forms.ProgressBar()
        Me.SuspendLayout()
        '
        'ListBox_TheFiles
        '
        Me.ListBox_TheFiles.Anchor = CType((((System.Windows.Forms.AnchorStyles.Top Or System.Windows.Forms.AnchorStyles.Bottom) _
                    Or System.Windows.Forms.AnchorStyles.Left) _
                    Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        Me.ListBox_TheFiles.FormattingEnabled = True
        Me.ListBox_TheFiles.Location = New System.Drawing.Point(12, 9)
        Me.ListBox_TheFiles.Name = "ListBox_TheFiles"
        Me.ListBox_TheFiles.Size = New System.Drawing.Size(528, 290)
        Me.ListBox_TheFiles.TabIndex = 0
        '
        'Button_Browse
        '
        Me.Button_Browse.Anchor = CType((System.Windows.Forms.AnchorStyles.Bottom Or System.Windows.Forms.AnchorStyles.Left), System.Windows.Forms.AnchorStyles)
        Me.Button_Browse.Location = New System.Drawing.Point(12, 312)
        Me.Button_Browse.Name = "Button_Browse"
        Me.Button_Browse.Size = New System.Drawing.Size(75, 23)
        Me.Button_Browse.TabIndex = 1
        Me.Button_Browse.Text = "Browse"
        Me.Button_Browse.UseVisualStyleBackColor = True
        '
        'Button_DoIT
        '
        Me.Button_DoIT.Anchor = CType((System.Windows.Forms.AnchorStyles.Bottom Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        Me.Button_DoIT.Location = New System.Drawing.Point(465, 312)
        Me.Button_DoIT.Name = "Button_DoIT"
        Me.Button_DoIT.Size = New System.Drawing.Size(75, 23)
        Me.Button_DoIT.TabIndex = 2
        Me.Button_DoIT.Text = "Do IT!"
        Me.Button_DoIT.UseVisualStyleBackColor = True
        '
        'ProgressBar_Main
        '
        Me.ProgressBar_Main.Anchor = CType(((System.Windows.Forms.AnchorStyles.Bottom Or System.Windows.Forms.AnchorStyles.Left) _
                    Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        Me.ProgressBar_Main.Location = New System.Drawing.Point(93, 312)
        Me.ProgressBar_Main.Name = "ProgressBar_Main"
        Me.ProgressBar_Main.Size = New System.Drawing.Size(366, 23)
        Me.ProgressBar_Main.Step = 1
        Me.ProgressBar_Main.TabIndex = 3
        '
        'WindowsUpdateInstaller_Form
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(552, 347)
        Me.Controls.Add(Me.ProgressBar_Main)
        Me.Controls.Add(Me.Button_DoIT)
        Me.Controls.Add(Me.Button_Browse)
        Me.Controls.Add(Me.ListBox_TheFiles)
        Me.Icon = CType(resources.GetObject("$this.Icon"), System.Drawing.Icon)
        Me.Name = "WindowsUpdateInstaller_Form"
        Me.Text = "Batch Windows Update Installer"
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents ListBox_TheFiles As System.Windows.Forms.ListBox
    Friend WithEvents Button_Browse As System.Windows.Forms.Button
    Friend WithEvents Button_DoIT As System.Windows.Forms.Button
    Friend WithEvents ProgressBar_Main As System.Windows.Forms.ProgressBar

End Class
