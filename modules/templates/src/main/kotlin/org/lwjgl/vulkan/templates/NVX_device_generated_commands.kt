/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan.templates

import org.lwjgl.generator.*
import org.lwjgl.vulkan.*

val NVX_device_generated_commands = "NVXDeviceGeneratedCommands".nativeClassVK("NVX_device_generated_commands", postfix = NVX) {
	documentation =
		"""
		This extension allows the device to generate a number of critical commands for command buffers.

		When rendering a large number of objects, the device can be leveraged to implement a number of critical functions, like updating matrices, or implementing occlusion culling, frustum culling, front to back sorting... Implementing those on the device does not require any special extension, since an application is free to define its own data structure, and just process them using shaders.

		However, if the application desires to quickly kick off the rendering of the final stream of objects, then unextended Vulkan forces the application to read back the processed stream and issue graphics command from the host. For very large scenes, the synchronization overhead, and cost to generate the command buffer can become the bottleneck. This extension allows an application to generate a device side stream of state changes and commands, and convert it efficiently into a command buffer without having to read it back on the host.

		Furthermore, it allows incremental changes to such command buffers, by manipulating only partial sections of a command stream, for example pipeline bindings. Unextended Vulkan requires re-creation of entire command buffers in such scenario, or updates synchronized on the host.

		The intended usage for this extension is for the application to:

		<ul>
			<li>create its objects as in unextended Vulkan</li>
			<li>create a VkObjectTableNVX, and register the various Vulkan objects that are needed to evaluate the input parameters.</li>
			<li>create a VkIndirectCommandsLayoutNVX, which lists the VkIndirectCommandsTokenTypes it wants to dynamically change as atomic command sequence. This step likely involves some internal device code compilation, since the intent is for the GPU to generate the command buffer in the pipeline.</li>
			<li>fill the input buffers with the data for each of the inputs it needs. Each input is an array that will be filled with an index in the object table, instead of using CPU pointers.</li>
			<li>set up a target secondary command buffer</li>
			<li>reserve command buffer space via vkCmdReserveSpaceForCommandsNVX in a target command buffer at the position you want the generated commands to be executed.</li>
			<li>call vkCmdProcessCommandsNVX to create the actual device commands for all sequences based on the array contents into a provided target command buffer.</li>
			<li>execute the target command buffer like a regular secondary command buffer</li>
		</ul>

		For each draw/dispatch, the following can be specified:

		<ul>
			<li>a different pipeline state object</li>
			<li>a number of descriptor sets, with dynamic offsets</li>
			<li>a number of vertex buffer bindings, with an optional dynamic offset</li>
			<li>a different index buffer, with an optional dynamic offset</li>
		</ul>

		It is recommended to register a small number of objects and to use dynamic offsets whenever possible.

		While the GPU can be faster than a CPU to generate the commands, it may not happen asynchronously, therefore the primary use-case is generating "less" total work (occlusion culling, classification to use specialized shaders...).
		"""

	IntConstant(
		"The extension specification version.",

		"NVX_DEVICE_GENERATED_COMMANDS_SPEC_VERSION".."1"
	)

	StringConstant(
		"The extension name.",

		"NVX_DEVICE_GENERATED_COMMANDS_EXTENSION_NAME".."VK_NVX_device_generated_commands"
	)

	EnumConstant(
		"Extends {@code VkStructureType}.",

		"STRUCTURE_TYPE_OBJECT_TABLE_CREATE_INFO_NVX".."1000086000",
		"STRUCTURE_TYPE_INDIRECT_COMMANDS_LAYOUT_CREATE_INFO_NVX".."1000086001",
		"STRUCTURE_TYPE_CMD_PROCESS_COMMANDS_INFO_NVX".."1000086002",
		"STRUCTURE_TYPE_CMD_RESERVE_SPACE_FOR_COMMANDS_INFO_NVX".."1000086003",
		"STRUCTURE_TYPE_DEVICE_GENERATED_COMMANDS_LIMITS_NVX".."1000086004",
		"STRUCTURE_TYPE_DEVICE_GENERATED_COMMANDS_FEATURES_NVX".."1000086005"
	)

	EnumConstant(
		"Extends {@code VkPipelineStageFlagBits}.",

		"PIPELINE_STAGE_COMMAND_PROCESS_BIT_NVX".enum(0x00020000)
	)

	EnumConstant(
		"""
		VkIndirectCommandsLayoutUsageFlagBitsNVX - Bitmask specifying allowed usage of a indirect commands layout

		<h5>Description</h5>
		For more information, see:

		<ul>
			<li>The reference page for ##VkIndirectCommandsLayoutCreateInfoNVX, where this interface is defined.</li>
			<li>The See Also section for other reference pages using this type.</li>
			<li>The Vulkan Specification.</li>
		</ul>

		<h5>See Also</h5>
		{@code VkIndirectCommandsLayoutUsageFlagsNVX}
		""",

		"INDIRECT_COMMANDS_LAYOUT_USAGE_UNORDERED_SEQUENCES_BIT_NVX".enum(0x00000001),
		"INDIRECT_COMMANDS_LAYOUT_USAGE_SPARSE_SEQUENCES_BIT_NVX".enum(0x00000002),
		"INDIRECT_COMMANDS_LAYOUT_USAGE_EMPTY_EXECUTIONS_BIT_NVX".enum(0x00000004),
		"INDIRECT_COMMANDS_LAYOUT_USAGE_INDEXED_SEQUENCES_BIT_NVX".enum(0x00000008)
	)

	EnumConstant(
		"""
		VkObjectEntryUsageFlagBitsNVX - Bitmask specifying allowed usage of an object entry

		<h5>Description</h5>
		For more information, see:

		<ul>
			<li>The reference page for ##VkObjectTableCreateInfoNVX, where this interface is defined.</li>
			<li>The See Also section for other reference pages using this type.</li>
			<li>The Vulkan Specification.</li>
		</ul>

		<h5>See Also</h5>
		{@code VkObjectEntryUsageFlagsNVX}
		""",

		"OBJECT_ENTRY_USAGE_GRAPHICS_BIT_NVX".enum(0x00000001),
		"OBJECT_ENTRY_USAGE_COMPUTE_BIT_NVX".enum(0x00000002)
	)

	EnumConstant(
		"""
		VkIndirectCommandsTokenTypeNVX - Enum specifying

		<h5>Description</h5>
		For more information, see:

		<ul>
			<li>The See Also section for other reference pages using this type.</li>
			<li>The Vulkan Specification.</li>
		</ul>

		<h5>See Also</h5>
		##VkIndirectCommandsLayoutTokenNVX, ##VkIndirectCommandsTokenNVX
		""",

		"INDIRECT_COMMANDS_TOKEN_PIPELINE_NVX".."0",
		"INDIRECT_COMMANDS_TOKEN_DESCRIPTOR_SET_NVX".."1",
		"INDIRECT_COMMANDS_TOKEN_INDEX_BUFFER_NVX".."2",
		"INDIRECT_COMMANDS_TOKEN_VERTEX_BUFFER_NVX".."3",
		"INDIRECT_COMMANDS_TOKEN_PUSH_CONSTANT_NVX".."4",
		"INDIRECT_COMMANDS_TOKEN_DRAW_INDEXED_NVX".."5",
		"INDIRECT_COMMANDS_TOKEN_DRAW_NVX".."6",
		"INDIRECT_COMMANDS_TOKEN_DISPATCH_NVX".."7"
	)

	EnumConstant(
		"""
		VkObjectEntryTypeNVX - Enum specifying object table entry type

		<h5>Description</h5>
		For more information, see:

		<ul>
			<li>The reference page for ##VkObjectTableCreateInfoNVX, where this interface is defined.</li>
			<li>The See Also section for other reference pages using this type.</li>
			<li>The Vulkan Specification.</li>
		</ul>

		<h5>See Also</h5>
		##VkObjectTableCreateInfoNVX, ##VkObjectTableDescriptorSetEntryNVX, ##VkObjectTableEntryNVX, ##VkObjectTableIndexBufferEntryNVX, ##VkObjectTablePipelineEntryNVX, ##VkObjectTablePushConstantEntryNVX, ##VkObjectTableVertexBufferEntryNVX, #UnregisterObjectsNVX()
		""",

		"OBJECT_ENTRY_DESCRIPTOR_SET_NVX".."0",
		"OBJECT_ENTRY_PIPELINE_NVX".."1",
		"OBJECT_ENTRY_INDEX_BUFFER_NVX".."2",
		"OBJECT_ENTRY_VERTEX_BUFFER_NVX".."3",
		"OBJECT_ENTRY_PUSH_CONSTANT_NVX".."4"
	)

	void(
		"CmdProcessCommandsNVX",
		"""
		Performs the generation of commands on the device.

		<h5>C Specification</h5>
		The actual generation on the device is handled with:

		<pre><code>void vkCmdProcessCommandsNVX(
￿    VkCommandBuffer                             commandBuffer,
￿    const VkCmdProcessCommandsInfoNVX*          pProcessCommandsInfo);</code></pre>

		<h5>Valid Usage (Implicit)</h5>
		<ul>
			<li>{@code commandBuffer} <b>must</b> be a valid {@code VkCommandBuffer} handle</li>
			<li>{@code pProcessCommandsInfo} <b>must</b> be a pointer to a valid ##VkCmdProcessCommandsInfoNVX structure</li>
			<li>{@code commandBuffer} <b>must</b> be in the recording state</li>
			<li>The {@code VkCommandPool} that {@code commandBuffer} was allocated from <b>must</b> support graphics, or compute operations</li>
			<li>This command <b>must</b> only be called inside of a render pass instance</li>
		</ul>

		<h5>Host Synchronization</h5>
		<ul>
			<li>Host access to {@code commandBuffer} <b>must</b> be externally synchronized</li>
			<li>Host access to the {@code VkCommandPool} that {@code commandBuffer} was allocated from <b>must</b> be externally synchronized</li>
		</ul>

		<h5>Command Properties</h5>
		<table class="lwjgl">
			<thead><tr><th>Command Buffer Levels</th><th>Render Pass Scope</th><th>Supported Queue Types</th></tr></thead>
			<tbody><tr><td>Primary Secondary</td><td>Inside</td><td>GRAPHICS COMPUTE</td></tr></tbody>
		</table>

		<h5>See Also</h5>
		##VkCmdProcessCommandsInfoNVX
		""",

		VkCommandBuffer.IN("commandBuffer", "the primary command buffer in which the generation process takes space."),
		const..VkCmdProcessCommandsInfoNVX.p.IN("pProcessCommandsInfo", "a pointer to an instance of the ##VkCmdProcessCommandsInfoNVX structure containing parameters affecting the processing of commands.")
	)

	void(
		"CmdReserveSpaceForCommandsNVX",
		"""
		Perform a reservation of command buffer space.

		<h5>C Specification</h5>
		Command space for generated commands recorded into a secondary command buffer <b>must</b> be reserved by calling:

		<pre><code>void vkCmdReserveSpaceForCommandsNVX(
￿    VkCommandBuffer                             commandBuffer,
￿    const VkCmdReserveSpaceForCommandsInfoNVX*  pReserveSpaceInfo);</code></pre>

		<h5>Valid Usage</h5>
		<ul>
			<li>The provided {@code commandBuffer} <b>must</b> not have had a prior space reservation since its creation or the last reset.</li>
			<li>The state of the {@code commandBuffer} <b>must</b> be legal to execute all commands within the sequence provided by the {@code indirectCommandsLayout} member of {@code pProcessCommandsInfo}.</li>
		</ul>

		<h5>Valid Usage (Implicit)</h5>
		<ul>
			<li>{@code commandBuffer} <b>must</b> be a valid {@code VkCommandBuffer} handle</li>
			<li>{@code pReserveSpaceInfo} <b>must</b> be a pointer to a valid ##VkCmdReserveSpaceForCommandsInfoNVX structure</li>
			<li>{@code commandBuffer} <b>must</b> be in the recording state</li>
			<li>The {@code VkCommandPool} that {@code commandBuffer} was allocated from <b>must</b> support graphics, or compute operations</li>
			<li>This command <b>must</b> only be called inside of a render pass instance</li>
			<li>{@code commandBuffer} <b>must</b> be a secondary {@code VkCommandBuffer}</li>
		</ul>

		<h5>Host Synchronization</h5>
		<ul>
			<li>Host access to {@code commandBuffer} <b>must</b> be externally synchronized</li>
			<li>Host access to the {@code VkCommandPool} that {@code commandBuffer} was allocated from <b>must</b> be externally synchronized</li>
		</ul>

		<h5>Command Properties</h5>
		<table class="lwjgl">
			<thead><tr><th>Command Buffer Levels</th><th>Render Pass Scope</th><th>Supported Queue Types</th></tr></thead>
			<tbody><tr><td>Secondary</td><td>Inside</td><td>GRAPHICS COMPUTE</td></tr></tbody>
		</table>

		<h5>See Also</h5>
		##VkCmdReserveSpaceForCommandsInfoNVX
		""",

		VkCommandBuffer.IN("commandBuffer", "the secondary command buffer in which the space for device-generated commands is reserved."),
		const..VkCmdReserveSpaceForCommandsInfoNVX.p.IN("pReserveSpaceInfo", "")
	)

	VkResult(
		"CreateIndirectCommandsLayoutNVX",
		"""
		(no short description available).

		<h5>C Specification</h5>
		Indirect command layouts are created by:

		<pre><code>VkResult vkCreateIndirectCommandsLayoutNVX(
￿    VkDevice                                    device,
￿    const VkIndirectCommandsLayoutCreateInfoNVX* pCreateInfo,
￿    const VkAllocationCallbacks*                pAllocator,
￿    VkIndirectCommandsLayoutNVX*                pIndirectCommandsLayout);</code></pre>

		<h5>Valid Usage (Implicit)</h5>
		<ul>
			<li>{@code device} <b>must</b> be a valid {@code VkDevice} handle</li>
			<li>{@code pCreateInfo} <b>must</b> be a pointer to a valid ##VkIndirectCommandsLayoutCreateInfoNVX structure</li>
			<li>If {@code pAllocator} is not {@code NULL}, {@code pAllocator} <b>must</b> be a pointer to a valid ##VkAllocationCallbacks structure</li>
			<li>{@code pIndirectCommandsLayout} <b>must</b> be a pointer to a {@code VkIndirectCommandsLayoutNVX} handle</li>
		</ul>

		<h5>Return Codes</h5>
		<dl>
			<dt>On success, this command returns</dt>
			<dd><ul>
				<li>#SUCCESS</li>
			</ul></dd>

			<dt>On failure, this command returns</dt>
			<dd><ul>
				<li>#ERROR_OUT_OF_HOST_MEMORY</li>
				<li>#ERROR_OUT_OF_DEVICE_MEMORY</li>
			</ul></dd>
		</dl>

		<h5>See Also</h5>
		##VkAllocationCallbacks, ##VkIndirectCommandsLayoutCreateInfoNVX
		""",

		VkDevice.IN("device", "the logical device that creates the object table."),
		const..VkIndirectCommandsLayoutCreateInfoNVX.p.IN("pCreateInfo", "a pointer to an instance of the ##VkObjectTableCreateInfoNVX structure containing parameters affecting creation of the table."),
		nullable..const..VkAllocationCallbacks.p.IN("pAllocator", "controls host memory allocation as described in the <a href=\"https://www.khronos.org/registry/vulkan/specs/1.0-extensions/xhtml/vkspec.html\\#memory-allocation\">Memory Allocation</a> chapter."),
		Check(1)..VkIndirectCommandsLayoutNVX.p.OUT("pIndirectCommandsLayout", "points to a {@code VkObjectTableNVX} handle in which the resulting object table is returned.")
	)

	void(
		"DestroyIndirectCommandsLayoutNVX",
		"""
		Destroy a object table.

		<h5>C Specification</h5>
		Indirect command layouts are destroyed by:

		<pre><code>void vkDestroyIndirectCommandsLayoutNVX(
￿    VkDevice                                    device,
￿    VkIndirectCommandsLayoutNVX                 indirectCommandsLayout,
￿    const VkAllocationCallbacks*                pAllocator);</code></pre>

		<h5>Valid Usage</h5>
		<ul>
			<li>All submitted commands that refer to {@code indirectCommandsLayout} <b>must</b> have completed execution</li>
			<li>If ##VkAllocationCallbacks were provided when {@code objectTable} was created, a compatible set of callbacks <b>must</b> be provided here</li>
			<li>If no ##VkAllocationCallbacks were provided when {@code objectTable} was created, {@code pAllocator} <b>must</b> be {@code NULL}</li>
		</ul>

		<h5>Valid Usage (Implicit)</h5>
		<ul>
			<li>{@code device} <b>must</b> be a valid {@code VkDevice} handle</li>
			<li>{@code indirectCommandsLayout} <b>must</b> be a valid {@code VkIndirectCommandsLayoutNVX} handle</li>
			<li>If {@code pAllocator} is not {@code NULL}, {@code pAllocator} <b>must</b> be a pointer to a valid ##VkAllocationCallbacks structure</li>
			<li>{@code indirectCommandsLayout} <b>must</b> have been created, allocated, or retrieved from {@code device}</li>
		</ul>

		<h5>See Also</h5>
		##VkAllocationCallbacks
		""",

		VkDevice.IN("device", "the logical device that destroys the layout."),
		VkIndirectCommandsLayoutNVX.IN("indirectCommandsLayout", "the table to destroy."),
		nullable..const..VkAllocationCallbacks.p.IN("pAllocator", "controls host memory allocation as described in the <a href=\"https://www.khronos.org/registry/vulkan/specs/1.0-extensions/xhtml/vkspec.html\\#memory-allocation\">Memory Allocation</a> chapter.")
	)

	VkResult(
		"CreateObjectTableNVX",
		"""
		(no short description available).

		<h5>C Specification</h5>
		To create object tables, call:

		<pre><code>VkResult vkCreateObjectTableNVX(
￿    VkDevice                                    device,
￿    const VkObjectTableCreateInfoNVX*           pCreateInfo,
￿    const VkAllocationCallbacks*                pAllocator,
￿    VkObjectTableNVX*                           pObjectTable);</code></pre>

		<h5>Valid Usage (Implicit)</h5>
		<ul>
			<li>{@code device} <b>must</b> be a valid {@code VkDevice} handle</li>
			<li>{@code pCreateInfo} <b>must</b> be a pointer to a valid ##VkObjectTableCreateInfoNVX structure</li>
			<li>If {@code pAllocator} is not {@code NULL}, {@code pAllocator} <b>must</b> be a pointer to a valid ##VkAllocationCallbacks structure</li>
			<li>{@code pObjectTable} <b>must</b> be a pointer to a {@code VkObjectTableNVX} handle</li>
		</ul>

		<h5>Return Codes</h5>
		<dl>
			<dt>On success, this command returns</dt>
			<dd><ul>
				<li>#SUCCESS</li>
			</ul></dd>

			<dt>On failure, this command returns</dt>
			<dd><ul>
				<li>#ERROR_OUT_OF_HOST_MEMORY</li>
				<li>#ERROR_OUT_OF_DEVICE_MEMORY</li>
			</ul></dd>
		</dl>

		<h5>See Also</h5>
		##VkAllocationCallbacks, ##VkObjectTableCreateInfoNVX
		""",

		VkDevice.IN("device", "the logical device that creates the object table."),
		const..VkObjectTableCreateInfoNVX.p.IN("pCreateInfo", "a pointer to an instance of the ##VkObjectTableCreateInfoNVX structure containing parameters affecting creation of the table."),
		nullable..const..VkAllocationCallbacks.p.IN("pAllocator", "controls host memory allocation as described in the <a href=\"https://www.khronos.org/registry/vulkan/specs/1.0-extensions/xhtml/vkspec.html\\#memory-allocation\">Memory Allocation</a> chapter."),
		Check(1)..VkObjectTableNVX.p.OUT("pObjectTable", "points to a {@code VkObjectTableNVX} handle in which the resulting object table is returned.")
	)

	void(
		"DestroyObjectTableNVX",
		"""
		Destroy a object table.

		<h5>C Specification</h5>
		To destroy an object table, call:

		<pre><code>void vkDestroyObjectTableNVX(
￿    VkDevice                                    device,
￿    VkObjectTableNVX                            objectTable,
￿    const VkAllocationCallbacks*                pAllocator);</code></pre>

		<h5>Valid Usage</h5>
		<ul>
			<li>All submitted commands that refer to {@code objectTable} <b>must</b> have completed execution.</li>
			<li>If ##VkAllocationCallbacks were provided when {@code objectTable} was created, a compatible set of callbacks <b>must</b> be provided here.</li>
			<li>If no ##VkAllocationCallbacks were provided when {@code objectTable} was created, {@code pAllocator} <b>must</b> be {@code NULL}.</li>
		</ul>

		<h5>Valid Usage (Implicit)</h5>
		<ul>
			<li>{@code device} <b>must</b> be a valid {@code VkDevice} handle</li>
			<li>{@code objectTable} <b>must</b> be a valid {@code VkObjectTableNVX} handle</li>
			<li>If {@code pAllocator} is not {@code NULL}, {@code pAllocator} <b>must</b> be a pointer to a valid ##VkAllocationCallbacks structure</li>
			<li>{@code objectTable} <b>must</b> have been created, allocated, or retrieved from {@code device}</li>
		</ul>

		<h5>Host Synchronization</h5>
		<ul>
			<li>Host access to {@code objectTable} <b>must</b> be externally synchronized</li>
		</ul>

		<h5>See Also</h5>
		##VkAllocationCallbacks
		""",

		VkDevice.IN("device", "the logical device that destroys the table."),
		VkObjectTableNVX.IN("objectTable", "the table to destroy."),
		nullable..const..VkAllocationCallbacks.p.IN("pAllocator", "controls host memory allocation as described in the <a href=\"https://www.khronos.org/registry/vulkan/specs/1.0-extensions/xhtml/vkspec.html\\#memory-allocation\">Memory Allocation</a> chapter.")
	)

	VkResult(
		"RegisterObjectsNVX",
		"""
		(no short description available).

		<h5>C Specification</h5>
		Resource bindings of Vulkan objects are registered at an arbitrary ftext:uint32_t index within an object table. As long as the object table references such objects, they must not be deleted.

		<pre><code>VkResult vkRegisterObjectsNVX(
￿    VkDevice                                    device,
￿    VkObjectTableNVX                            objectTable,
￿    uint32_t                                    objectCount,
￿    const VkObjectTableEntryNVX* const*         ppObjectTableEntries,
￿    const uint32_t*                             pObjectIndices);</code></pre>

		<h5>Valid Usage</h5>
		<ul>
			<li>The contents of {@code pObjectTableEntry} must yield plausible bindings supported by the device.</li>
			<li>At any {@code pObjectIndices} there <b>must</b> not be a registered resource already.</li>
			<li>Any value inside {@code pObjectIndices} <b>must</b> be below the appropriate ##VkObjectTableCreateInfoNVX{@code ::pObjectEntryCounts} limits provided at {@code objectTable} creation time.</li>
		</ul>

		<h5>Valid Usage (Implicit)</h5>
		<ul>
			<li>{@code device} <b>must</b> be a valid {@code VkDevice} handle</li>
			<li>{@code objectTable} <b>must</b> be a valid {@code VkObjectTableNVX} handle</li>
			<li>{@code ppObjectTableEntries} <b>must</b> be a pointer to an array of {@code objectCount} valid ##VkObjectTableEntryNVX structures</li>
			<li>{@code pObjectIndices} <b>must</b> be a pointer to an array of {@code objectCount} {@code uint32_t} values</li>
			<li>{@code objectCount} <b>must</b> be greater than 0</li>
			<li>{@code objectTable} <b>must</b> have been created, allocated, or retrieved from {@code device}</li>
		</ul>

		<h5>Host Synchronization</h5>
		<ul>
			<li>Host access to {@code objectTable} <b>must</b> be externally synchronized</li>
		</ul>

		<h5>Return Codes</h5>
		<dl>
			<dt>On success, this command returns</dt>
			<dd><ul>
				<li>#SUCCESS</li>
			</ul></dd>

			<dt>On failure, this command returns</dt>
			<dd><ul>
				<li>#ERROR_OUT_OF_HOST_MEMORY</li>
				<li>#ERROR_OUT_OF_DEVICE_MEMORY</li>
			</ul></dd>
		</dl>

		<h5>See Also</h5>
		##VkObjectTableEntryNVX
		""",

		VkDevice.IN("device", "the logical device that creates the object table."),
		VkObjectTableNVX.IN("objectTable", "the table for which the resources are registered."),
		AutoSize("ppObjectTableEntries", "pObjectIndices")..uint32_t.IN("objectCount", "the number of resources to register."),
		const..VkObjectTableEntryNVX.p.const.p.IN("ppObjectTableEntries", "provides an array for detailed binding informations, each array element is a pointer to a struct of type ##VkObjectTablePipelineEntryNVX, ##VkObjectTableDescriptorSetEntryNVX, ##VkObjectTableVertexBufferEntryNVX, ##VkObjectTableIndexBufferEntryNVX or ##VkObjectTablePushConstantEntryNVX (see below for details)."),
		const..uint32_t_p.IN("pObjectIndices", "are the indices at which each resource is registered.")
	)

	VkResult(
		"UnregisterObjectsNVX",
		"""
		(no short description available).

		<h5>C Specification</h5>
		Using the following command to unregister resources from an object table:

		<pre><code>VkResult vkUnregisterObjectsNVX(
￿    VkDevice                                    device,
￿    VkObjectTableNVX                            objectTable,
￿    uint32_t                                    objectCount,
￿    const VkObjectEntryTypeNVX*                 pObjectEntryTypes,
￿    const uint32_t*                             pObjectIndices);</code></pre>

		<h5>Valid Usage</h5>
		<ul>
			<li>At any {@code pObjectIndices} there <b>must</b> be a registered resource already.</li>
			<li>The {@code pObjectEntryTypes} of the resource at {@code pObjectIndices} <b>must</b> match.</li>
			<li>All operations on the device using the registered resource must have been completed.</li>
		</ul>

		<h5>Valid Usage (Implicit)</h5>
		<ul>
			<li>{@code device} <b>must</b> be a valid {@code VkDevice} handle</li>
			<li>{@code objectTable} <b>must</b> be a valid {@code VkObjectTableNVX} handle</li>
			<li>{@code pObjectEntryTypes} <b>must</b> be a pointer to an array of {@code objectCount} valid {@code VkObjectEntryTypeNVX} values</li>
			<li>{@code pObjectIndices} <b>must</b> be a pointer to an array of {@code objectCount} {@code uint32_t} values</li>
			<li>{@code objectCount} <b>must</b> be greater than 0</li>
			<li>{@code objectTable} <b>must</b> have been created, allocated, or retrieved from {@code device}</li>
		</ul>

		<h5>Host Synchronization</h5>
		<ul>
			<li>Host access to {@code objectTable} <b>must</b> be externally synchronized</li>
		</ul>

		<h5>Return Codes</h5>
		<dl>
			<dt>On success, this command returns</dt>
			<dd><ul>
				<li>#SUCCESS</li>
			</ul></dd>

			<dt>On failure, this command returns</dt>
			<dd><ul>
				<li>#ERROR_OUT_OF_HOST_MEMORY</li>
				<li>#ERROR_OUT_OF_DEVICE_MEMORY</li>
			</ul></dd>
		</dl>
		""",

		VkDevice.IN("device", "the logical device that creates the object table."),
		VkObjectTableNVX.IN("objectTable", "the table from which the resources are unregistered."),
		AutoSize("pObjectEntryTypes", "pObjectIndices")..uint32_t.IN("objectCount", "the number of resources being removed from the object table."),
		const..VkObjectEntryTypeNVX.p.IN("pObjectEntryTypes", ""),
		const..uint32_t_p.IN("pObjectIndices", "provides the array of object indices to be removed.")
	)

	void(
		"GetPhysicalDeviceGeneratedCommandsPropertiesNVX",
		"""
		Returns device-generated commands related properties of a physical device.

		<h5>C Specification</h5>
		To query the support of related features and limitations, call:

		<pre><code>void vkGetPhysicalDeviceGeneratedCommandsPropertiesNVX(
￿    VkPhysicalDevice                            physicalDevice,
￿    VkDeviceGeneratedCommandsFeaturesNVX*       pFeatures,
￿    VkDeviceGeneratedCommandsLimitsNVX*         pLimits);</code></pre>

		<h5>Valid Usage (Implicit)</h5>
		<ul>
			<li>{@code physicalDevice} <b>must</b> be a valid {@code VkPhysicalDevice} handle</li>
			<li>{@code pFeatures} <b>must</b> be a pointer to a ##VkDeviceGeneratedCommandsFeaturesNVX structure</li>
			<li>{@code pLimits} <b>must</b> be a pointer to a ##VkDeviceGeneratedCommandsLimitsNVX structure</li>
		</ul>

		<h5>See Also</h5>
		##VkDeviceGeneratedCommandsFeaturesNVX, ##VkDeviceGeneratedCommandsLimitsNVX
		""",

		VkPhysicalDevice.IN("physicalDevice", "the handle to the physical device whose properties will be queried."),
		VkDeviceGeneratedCommandsFeaturesNVX.p.OUT("pFeatures", "points to an instance of the ##VkDeviceGeneratedCommandsFeaturesNVX structure, that will be filled with returned information."),
		VkDeviceGeneratedCommandsLimitsNVX.p.OUT("pLimits", "points to an instance of the ##VkDeviceGeneratedCommandsLimitsNVX structure, that will be filled with returned information.")
	)
}